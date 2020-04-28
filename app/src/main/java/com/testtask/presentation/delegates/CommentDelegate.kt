package com.testtask.presentation.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.testtask.R
import com.testtask.domain.model.Comment
import com.testtask.domain.model.delegate.DisplayableItem


class CommentDelegate(fragment:Fragment) : AdapterDelegate<List<DisplayableItem>>() {

    private val inflater: LayoutInflater = fragment.layoutInflater

    public override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is Comment
    }

    public override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return CommentViewHolder(inflater.inflate(R.layout.item_comment, parent, false))
    }

    public override fun onBindViewHolder(
        items: List<DisplayableItem>, position: Int,
        holder: ViewHolder, @Nullable payloads: List<Any>
    ) {
        val vh = holder as CommentViewHolder
        val post: Comment = items[position] as Comment
        vh.name.text = "${post.name} (${post.email})"
        vh.body.text = post.body
    }

    internal class CommentViewHolder(itemView: View) : ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var body: TextView = itemView.findViewById(R.id.body)
    }

}