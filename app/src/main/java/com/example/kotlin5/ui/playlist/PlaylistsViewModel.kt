package com.example.kotlin5.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin5.App
import com.example.kotlin5.BuildConfig.API_KEY
import com.example.kotlin5.common.constants.Constant
import com.example.kotlin5.core.network.status.Resource
import com.example.kotlin5.data.remote.apiservices.ApiService
import com.example.kotlin5.ui.base.BaseViewModel
import com.example.kotlin5.data.remote.RetrofitClient
import com.example.kotlin5.data.remote.dto.Playlist

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel : BaseViewModel() {

    fun getPlaylists(): LiveData<Resource<Playlist>> {
        return playlist()
    }

    private fun playlist(): LiveData<Resource<Playlist>> {
        var data = MutableLiveData<Resource<Playlist>>()
        data = App.playlistRepository.getPlayList() as MutableLiveData<Resource<Playlist>>
        return data
    }
}