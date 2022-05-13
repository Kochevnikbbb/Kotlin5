package com.example.kotlin5.extensions

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide


fun ImageView.loadWithGlide(url: String) {
    Glide.with(this).load(url).centerCrop().into(this)
}
fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
