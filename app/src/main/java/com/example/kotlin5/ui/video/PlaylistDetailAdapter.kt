package com.example.kotlin5.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin5.common.extensions.loadWithGlide
import com.example.kotlin5.data.remote.dto.Item
import com.example.kotlin5.databinding.Item2Binding

class PlaylistDetailAdapter(
    private val onItemClick: (id: String) -> Unit
) : RecyclerView.Adapter<PlaylistDetailAdapter.ListViewHolder>() {

    private var list = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            Item2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: ArrayList<Item>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: Item2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(playlist: Item) {
            binding.apply {
                tvPlaylistTitle.text = playlist.snippet?.title.toString()
                playlist.snippet?.thumbnails?.medium?.url?.let { ivPlaylists.loadWithGlide(it) }
                tvCountOfVideos.text = playlist.kind.toString()
            }
        }
    }
}