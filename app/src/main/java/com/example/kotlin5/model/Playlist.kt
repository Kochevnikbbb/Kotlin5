package com.example.kotlin5.model

import com.google.gson.annotations.SerializedName

data class Playlist(
    val kind:String? = null,
    @SerializedName("etag")
    val tag: String? = null,
)
