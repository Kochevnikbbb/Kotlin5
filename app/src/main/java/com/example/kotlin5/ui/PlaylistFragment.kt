package com.example.kotlin5.ui

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin5.base.BaseNavFragment
import com.example.kotlin5.base.BaseViewModel
import com.example.kotlin5.databinding.FragmentPlaylistBinding
import com.example.kotlin5.extensions.InternetChecker
import com.example.kotlin5.extensions.NetworkStatus
import com.example.kotlin5.interfaces.SomethingClicked
import com.example.kotlin5.model.Item

class PlaylistFragment : BaseNavFragment<FragmentPlaylistBinding, BaseViewModel>(),
    SomethingClicked {
    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(requireActivity())[PlaylistsViewModel::class.java]
    }

    override fun checkInternet() {
        super.checkInternet()
        InternetChecker(requireContext()).observe(this) {
            when (it) {
                NetworkStatus.Available -> initViewModel()
                NetworkStatus.Unavailable -> View.VISIBLE
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.playlists().observe(this) {
            binding.rec.layoutManager = LinearLayoutManager(requireContext())
            val list = it.items as ArrayList<Item>
            binding.rec.adapter = PlaylistAdapter(list, this, requireContext())
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentPlaylistBinding {
        return FragmentPlaylistBinding.inflate(inflater)
    }

    override fun clickOnItem(any: Any) {
        if (any is Item) {
            navigate(PlaylistFragmentDirections
                .actionPlaylistFragmentToVideoFragment(any.id))
        }
    }
}