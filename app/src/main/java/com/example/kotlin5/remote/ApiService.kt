package com.example.kotlin5.remote

import com.example.kotlin5.model.Playlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("channelId") channelId: String,
    ): Call<Playlist>
}