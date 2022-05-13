package com.example.kotlin5.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin5.databinding.ItemBinding
import com.example.kotlin5.extensions.loadWithGlide
import com.example.kotlin5.model.Item


class PlaylistAdapter(
    private val onItemClick: (id: String) -> Unit
) : RecyclerView.Adapter<PlaylistAdapter.ListViewHolder>() {

    private var list = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemBinding.inflate(
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

    inner class ListViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(playlist: Item) {
            playlist.snippet.thumbnails.medium.url.let {
                binding.ivPlaylists.loadWithGlide(
                    it
                )
            }
            binding.tvPlaylistTitle.text = playlist.snippet.title.toString()
            (playlist.contentDetails.itemCount.toString() + " video series").also {
                binding.tvCountOfVideos.text = it
            }
            binding.root.setOnClickListener {
                playlist.id.let { it1 -> onItemClick(it1) }
            }
        }
    }
}