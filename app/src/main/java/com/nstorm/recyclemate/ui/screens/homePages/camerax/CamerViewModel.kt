package com.nstorm.recyclemate.ui.screens.homePages.camerax

import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import okhttp3.RequestBody.Companion.toRequestBody

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.ByteArrayOutputStream

interface YourApi {
    @Multipart
    @POST("predict")
    fun uploadImage(@Part image: MultipartBody.Part): Call<ImageUploadResponse>
}

data class ApiResponse(
    val classes: List<String>
)

data class ImageUploadRequest(val image: String)
data class ImageUploadResponse(
    val success: Boolean,
    val message: String,
    val classes: List<String>
)

data class UiState(
    val isLoading: Boolean = false,
    val response: String = ""
)

@HiltViewModel
class CamerViewModel @Inject constructor() : ViewModel() {
    private var capturedImage: MutableLiveData<Bitmap> = MutableLiveData()

    private  val _uiState = MutableStateFlow(UiState())

    val uiState: StateFlow<UiState> get() = _uiState
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.107.235:5000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val yourApi = retrofit.create(YourApi::class.java)

    fun updateImage(image: Bitmap) {
        _uiState.value = uiState.value.copy(isLoading = true)
        capturedImage.postValue(image)
        uploadImageToApi(image)
    }

    private fun uploadImageToApi(bitmap: Bitmap) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()

        Log.d("testing", "Well something is working")

        val requestBody = byteArray.toRequestBody("image/*".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Part.createFormData("file", "image.png", requestBody)

        yourApi.uploadImage(multipartBody).enqueue(object : Callback<ImageUploadResponse> {
            override fun onResponse(
                call: Call<ImageUploadResponse>,
                response: Response<ImageUploadResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let {
                        val apiResponse = ApiResponse(it.classes)
                        _uiState.value = uiState.value.copy(response = apiResponse.toString())
                        // Now you have the classes in your ApiResponse object
                        // You can process it as needed
                        Log.d("testing", apiResponse.toString())
                        _uiState.value = uiState.value.copy(isLoading = false)
                    }
                } else {
                    Log.d("testing", response.toString())
                    // Handle the error
                    _uiState.value = uiState.value.copy(isLoading = false)
                }
            }

            override fun onFailure(call: Call<ImageUploadResponse>, t: Throwable) {
                // Handle the failure
                _uiState.value = uiState.value.copy(isLoading = false)
            }
        }
        )
    }

}

private fun convertBitmapToBase64(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}