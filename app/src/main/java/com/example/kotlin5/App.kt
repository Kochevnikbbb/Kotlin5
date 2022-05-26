package com.example.kotlin5

import android.app.Application
import com.example.kotlin5.data.remote.RemoteDataSource
import com.example.kotlin5.data.remote.apiservices.ApiService
import com.example.kotlin5.data.repositories.DetailPlaylistRepository
import com.example.kotlin5.data.repositories.PlaylistRepository
import com.example.kotlin5.data.remote.RetrofitClient

class App : Application() {

    companion object {
        private val dataSource by lazy {
            RemoteDataSource(apiService)
        }

        private val apiService: ApiService by lazy {
            RetrofitClient.create()
        }

        val playlistRepository: PlaylistRepository by lazy {
            PlaylistRepository(dataSource)
        }

        val detailPlaylistRepository: DetailPlaylistRepository by lazy {
            DetailPlaylistRepository(dataSource)
        }
    }
}