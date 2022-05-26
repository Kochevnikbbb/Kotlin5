package com.example.kotlin5.data.remote

import com.example.kotlin5.BuildConfig.API_KEY
import com.example.kotlin5.common.constants.Constant
import com.example.kotlin5.core.network.BaseDataSource
import com.example.kotlin5.data.remote.apiservices.ApiService

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getPlaylist() = getResult {
        apiService.getPlaylists(Constant.part, API_KEY, Constant.channelId, 50)
    }

    suspend fun getPlaylistItem(id: String) = getResult {
        apiService.getPlaylistItem(Constant.part, API_KEY, id, 50)
    }

}