package com.nstorm.recyclemate.ui.screens.homePages.camerax

import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LifecycleOwner
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Composable
fun CameraScreen(
    lifecycleOwner: LifecycleOwner,
    viewmodel: CamerViewModel
) {
    val context = LocalContext.current
    val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()

    val imageCapture = remember { ImageCapture.Builder().build() }

    var showDialog by remember { mutableStateOf(false) }
    var imageBitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }

    val scope = CoroutineScope(Dispatchers.IO)

    val uiState by viewmodel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState.isLoading) {
        if(!uiState.isLoading) {
            showDialog = false
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
        ) {
            AndroidView(factory = { context ->
                val preview = Preview.Builder().build()

                val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                cameraProviderFuture.addListener({
                    val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                    try {
                        cameraProvider.unbindAll()

                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            CameraSelector.DEFAULT_BACK_CAMERA,
                            preview,
                            imageCapture
                        )
                    } catch (exc: Exception) {
                        // Log error
                    }

                }, ContextCompat.getMainExecutor(context))

                val previewView = PreviewView(context)
                preview.setSurfaceProvider(previewView.surfaceProvider)

                previewView
            }, modifier = Modifier.fillMaxSize())

            Button(onClick = {
                val photoFile = File(
                    context.externalMediaDirs.first(),
                    "${System.currentTimeMillis()}.jpg"
                )

                val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

                imageCapture.takePicture(
                    outputOptions,
                    cameraExecutor,
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                            // Handle the image file
                            showDialog = true
                            val imageLoader = ImageLoader(context)
                            val request = ImageRequest.Builder(context)
                                .data(photoFile)
                                .build()
                            scope.launch {
                                val result =
                                    (imageLoader.execute(request) as SuccessResult).drawable
                                imageBitmap = result.toBitmap()
                                viewmodel.updateImage(imageBitmap!!) // send the image to ViewModel
                            }
                        }

                        override fun onError(exception: ImageCaptureException) {
                            // Handle the error
                        }
                    }
                )
            }, modifier = Modifier.align(Alignment.BottomCenter)) {
                Text(text = "Take Photo")
            }
        }
        
        
        
        Text(text = uiState.response)

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    imageBitmap?.let { bitmap ->
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "Captured photo"
                        )
                    }
                    if (uiState.isLoading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}