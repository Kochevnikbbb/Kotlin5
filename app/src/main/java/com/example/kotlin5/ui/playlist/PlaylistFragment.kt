package com.example.kotlin5.ui.playlist

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin5.R
import com.example.kotlin5.common.constants.Constant
import com.example.kotlin5.core.network.status.Status
import com.example.kotlin5.databinding.FragmentPlaylistBinding
import com.example.kotlin5.ui.base.BaseNavFragment
import com.example.kotlin5.ui.base.BaseViewModel
import com.example.youtube40.core.utils.ConnectivityStatus
import com.example.kotlin5.data.remote.dto.Item

class PlaylistFragment : BaseNavFragment<FragmentPlaylistBinding, BaseViewModel>() {

    private val adapter = PlaylistAdapter(this::clickOnItem)
    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun initView() {
        getNavigation()
        binding.rec.adapter = adapter
        binding.rec.layoutManager = LinearLayoutManager(requireContext())
    }

    /* private fun onItemClick() {
         val bundle = Bundle()
         bundle.putString("sdgasdgsd", Constant.putId)
         findNavController().navigate(R.id.videoFragment, bundle)
     }*/

    override fun initViewModel() {
        viewModel.getPlaylists().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.setList(it.data?.items as ArrayList<Item>)
                    binding.progressBar.isVisible = false
                }
                Status.ERROR -> {}
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
            }
        }

    }

    override fun initListener() {
        binding.included.btnTrySwitchOnInternet.setOnClickListener {
            checkInternet()
        }
    }


    override fun inflateViewBinding(inflater: LayoutInflater): FragmentPlaylistBinding {
        return FragmentPlaylistBinding.inflate(inflater)
    }

    override fun checkInternet() {

        val checkNet = ConnectivityStatus(requireContext())
        checkNet.observe(this) {
            binding.progressBar.isVisible = true
            if (it) {
                binding.progressBar.isVisible = false
                binding.rec.isVisible = true
                binding.included.root.isInvisible = true
                initViewModel()
            } else {
                binding.progressBar.isVisible = false
                binding.rec.isInvisible = true
                binding.included.root.isVisible = true
            }
        }
    }

    private fun clickOnItem(id: String) {
        val bundle = Bundle()
        bundle.putString(Constant.putId, id)
        findNavController().navigate(R.id.videoFragment, bundle)
    }
}