package com.example.kotlin5.ui.playlist

import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin5.base.BaseNavFragment
import com.example.kotlin5.base.BaseViewModel
import com.example.kotlin5.checkInternet.ConnectionLiveData
import com.example.kotlin5.databinding.FragmentPlaylistBinding
import com.example.kotlin5.interfaces.SomethingClicked
import com.example.kotlin5.model.Item

class PlaylistFragment : BaseNavFragment<FragmentPlaylistBinding, BaseViewModel>(),
    SomethingClicked {

    private val adapter = PlaylistAdapter(this::clickOnItem)
    private lateinit var checkNet: ConnectionLiveData

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun initView() {
        binding.rec.adapter = adapter
        binding.rec.layoutManager = LinearLayoutManager(context)
    }

    override fun initViewModel() {
        viewModel.getPlaylists().observe(this) {
           // Toast.makeText(this, it.kind.toString(), Toast.LENGTH_SHORT).show()
            adapter.setList(it.items as ArrayList<Item>)
        }
    }


    override fun inflateViewBinding(inflater: LayoutInflater): FragmentPlaylistBinding {
        return FragmentPlaylistBinding.inflate(inflater)
    }

    override fun checkInternet() {
        checkNet = ConnectionLiveData(requireContext())
        checkNet.observe(this) {
            if (it) {
                initViewModel()
                binding.rec.isVisible = true
                binding.included.root.isInvisible = true
            } else {
                binding.rec.isInvisible = true
                binding.included.root.isVisible = true
            }
        }
    }

    override fun clickOnItem(any: Any) {
        if (any is Item) {
            navigate(
                PlaylistFragmentDirections.actionPlaylistFragmentToVideoFragment(
                    any.id
                )
            )
        }
    }
}