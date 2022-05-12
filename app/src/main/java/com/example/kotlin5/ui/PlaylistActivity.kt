package com.example.kotlin5.ui

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin5.base.BaseActivity
import com.example.kotlin5.databinding.ActivityPlaylistBinding
import com.example.kotlin5.ui.playlist.PlaylistsViewModel

class PlaylistActivity : BaseActivity<PlaylistsViewModel, ActivityPlaylistBinding>() {

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel.playlists().observe(this) {
            Toast.makeText(this,it.kind, Toast.LENGTH_SHORT).show()
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(inflater)
    }
}