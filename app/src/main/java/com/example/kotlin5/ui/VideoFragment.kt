package com.example.kotlin5.ui

import android.os.Bundle
import android.view.LayoutInflater
import com.example.kotlin5.base.BaseNavFragment
import com.example.kotlin5.base.BaseViewModel
import com.example.kotlin5.databinding.FragmentVideoBinding
import com.example.kotlin5.extensions.showToast


class VideoFragment : BaseNavFragment<FragmentVideoBinding, BaseViewModel>() {
    private lateinit var args: VideoFragmentArgs
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = VideoFragmentArgs.fromBundle(requireArguments())
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentVideoBinding {
        return FragmentVideoBinding.inflate(inflater)
    }


    override fun initView() {
        super.initView()
        args.id?.let { requireContext().showToast(it) }

    }
}