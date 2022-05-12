package com.example.kotlin5.ui.playlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin5.R
import com.example.kotlin5.databinding.ItemBinding
import com.example.kotlin5.extensions.chooseTheMostQualityImage
import com.example.kotlin5.extensions.glideSetter
import com.example.kotlin5.interfaces.SomethingClicked
import com.example.kotlin5.model.Item


class PlaylistAdapter(private var list: List<Item>, private var onItemClick: SomethingClicked,
                      private val context: Context) : RecyclerView.Adapter<PlaylistAdapter.PlaylistsHolder>() {


    class PlaylistsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemBinding.bind(itemView)
        fun bind(i: Item, context: Context) = with(binding) {
            val uri = context.chooseTheMostQualityImage(i)
            context.glideSetter(uri, ivPlaylists)
            tvPlaylistTitle.text = i.snippet.title
            val itemCount = i.contentDetails.itemCount.toString()
            tvCountOfVideos.text = context.getString(R.string.videos, itemCount)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return PlaylistsHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistsHolder, position: Int) {
        holder.bind(list[position], context)
        holder.itemView.setOnClickListener{
            onItemClick.clickOnItem(list[position])
        }
    }

    override fun getItemCount(): Int = list.size
}