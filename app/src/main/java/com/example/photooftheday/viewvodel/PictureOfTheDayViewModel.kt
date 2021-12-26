package com.example.photooftheday.viewvodel

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photooftheday.BuildConfig
import com.example.photooftheday.repository.PictureOfTheDayRetrofitImpl
import com.example.photooftheday.repository.PictureOfTheDayResponseData

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PictureOfTheDayViewModel (
    private val liveDataForViewToObserve: MutableLiveData<PictureOfTheDayState> = MutableLiveData(),
    private val retrofitImpl: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl()
    ) : ViewModel() {
        fun getData(): LiveData<PictureOfTheDayState> {
            return liveDataForViewToObserve
        }

        fun sendServerRequest() {
            liveDataForViewToObserve.value = PictureOfTheDayState.Loading(0)
            val apiKey: String = BuildConfig.NASA_API_KEY
            if (apiKey.isBlank()) {
                liveDataForViewToObserve.value = PictureOfTheDayState.Error(Throwable("wrong key"))
            } else {
                retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey).enqueue(callback)
            }
        }

        private val callback = object : Callback<PictureOfTheDayResponseData>{
            override fun onResponse(
                call: Call<PictureOfTheDayResponseData>,
                response: Response<PictureOfTheDayResponseData>
            ) {
                if(response.isSuccessful&&response.body()!=null){
                    liveDataForViewToObserve.value = PictureOfTheDayState.Success(response.body()!!)
                }else{
                    liveDataForViewToObserve.value = PictureOfTheDayState.Error(IllegalStateException("Ошибка"))
                }
            }

            override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
                liveDataForViewToObserve.value = PictureOfTheDayState.Error(IllegalStateException("onFailure"))
            }

        }
}