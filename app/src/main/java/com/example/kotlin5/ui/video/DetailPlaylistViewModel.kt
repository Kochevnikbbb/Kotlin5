package com.example.kotlin5.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.kotlin5.App
import com.example.kotlin5.core.network.status.Resource
import com.example.kotlin5.ui.base.BaseViewModel
import com.example.kotlin5.data.remote.dto.Playlist

class DetailPlaylistViewModel : BaseViewModel() {

    fun getPlaylists(id: String): LiveData<Resource<Playlist>> {
        return playlist(id)
    }

    private fun playlist(id: String): LiveData<Resource<Playlist>> {
        var data = MutableLiveData<Resource<Playlist>>()
        data = App.detailPlaylistRepository.getPlayList(id) as MutableLiveData<Resource<Playlist>>
        return data
    }
}