package com.testtask.presentation.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.testtask.R
import com.testtask.domain.model.Post
import com.testtask.domain.model.delegate.DisplayableItem


class PostDelegate(fragment:Fragment, private val listener: (Post) -> Unit) : AdapterDelegate<List<DisplayableItem>>() {

    private val inflater: LayoutInflater = fragment.layoutInflater

    public override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is Post
    }

    public override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return PostViewHolder(inflater.inflate(R.layout.item_post, parent, false))
    }

    public override fun onBindViewHolder(
        items: List<DisplayableItem>, position: Int,
        holder: ViewHolder, @Nullable payloads: List<Any>
    ) {
        val vh = holder as PostViewHolder
        val post: Post = items[position] as Post
        vh.title.text = post.title
        vh.body.text = post.body
        vh.frClick.setOnClickListener {
            listener.invoke(post)
        }
    }

    internal class PostViewHolder(itemView: View) : ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var body: TextView = itemView.findViewById(R.id.body)
        var frClick: FrameLayout = itemView.findViewById(R.id.frClick)
    }

}