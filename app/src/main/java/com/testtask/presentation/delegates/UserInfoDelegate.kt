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
import com.testtask.domain.model.UserInfo
import com.testtask.domain.model.delegate.DisplayableItem

class UserInfoDelegate(fragment: Fragment) : AdapterDelegate<List<DisplayableItem>>() {

    private val inflater: LayoutInflater = fragment.layoutInflater

    public override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is UserInfo
    }

    public override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return UserInfoViewHolder(inflater.inflate(R.layout.item_userinfo, parent, false))
    }

    public override fun onBindViewHolder(
        items: List<DisplayableItem>, position: Int,
        holder: ViewHolder, @Nullable payloads: List<Any>
    ) {
        val vh = holder as UserInfoViewHolder
        val post: UserInfo = items[position] as UserInfo
        vh.name.text = "${post.userName} (${post.name})"
        vh.address.text = post.address
        vh.phone.text = post.phone
        vh.website.text = post.website
        vh.companyName.text = post.companyName
    }

    internal class UserInfoViewHolder(itemView: View) : ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tvName)
        var address: TextView = itemView.findViewById(R.id.tvAddress)
        var phone: TextView = itemView.findViewById(R.id.tvPhone)
        var website: TextView = itemView.findViewById(R.id.tvWebsite)
        var companyName: TextView = itemView.findViewById(R.id.tvCompanyName)
    }

}