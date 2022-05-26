package com.example.kotlin5.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin5.common.constants.Constant
import com.example.kotlin5.core.network.status.Status
import com.example.kotlin5.data.remote.dto.Item
import com.example.kotlin5.databinding.FragmentVideoBinding
import com.example.kotlin5.ui.base.BaseNavFragment
import com.example.kotlin5.ui.base.BaseViewModel
import com.example.youtube40.core.utils.ConnectivityStatus


class VideoFragment : BaseNavFragment<FragmentVideoBinding, BaseViewModel>() {
    private lateinit var args: VideoFragmentArgs

    override val viewModel: DetailPlaylistViewModel by lazy {
        ViewModelProvider(this)[DetailPlaylistViewModel::class.java]
    }
    private val adapter =PlaylistDetailAdapter(this::onItemClick)

    private fun onItemClick(id: String) {
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentVideoBinding {
        return FragmentVideoBinding.inflate(inflater)
    }

    override fun initView() {
        binding.recyclerPlaylistDetail.adapter = adapter
        binding.recyclerPlaylistDetail.layoutManager=LinearLayoutManager(requireContext())
    }

    override fun initViewModel() {
        arguments?.get(Constant.putId)?.let { it ->
            viewModel.getPlaylists(it as String).observe(this){
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.isVisible = false
                        adapter.setList(it.data?.items as ArrayList<Item>)
                    }
                    Status.ERROR -> {}
                    Status.LOADING -> {
                        binding.progressBar.isVisible = true
                    }
                }
            }
        }
    }


    override fun checkInternet() {
        val checkNet = ConnectivityStatus(requireContext())
        checkNet.observe(this) {
            binding.progressBar.isVisible = true
            if (it) {
                binding.progressBar.isVisible = false
                binding.noInternetCheck.root.isInvisible = true
                binding.recyclerPlaylistDetail.isVisible = true
                initViewModel()
            } else {
                binding.progressBar.isVisible = false
                binding.recyclerPlaylistDetail.isInvisible = true
                binding.noInternetCheck.root.isVisible = true
            }
        }
    }
}