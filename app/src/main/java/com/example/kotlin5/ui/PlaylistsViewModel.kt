package com.example.kotlin5.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin5.BuildConfig.API_KEY
import com.example.kotlin5.`object`.Constant
import com.example.kotlin5.base.BaseViewModel
import com.example.kotlin5.model.Playlist
import com.example.kotlin5.remote.ApiService
import com.example.kotlin5.remote.RetrofitClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel : BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlists(): LiveData<Playlist> {
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<Playlist> {
        val data = MutableLiveData<Playlist>()

        apiService.getPlaylists(Constant.part, Constant.channelId, API_KEY, Constant.maxResult)
            .enqueue(object : Callback<Playlist> {
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                    // 200-299
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    // 404 - not found, 401 - токен истек 400-499
                    print(t.stackTrace)
                }
            })

        return data
    }
}