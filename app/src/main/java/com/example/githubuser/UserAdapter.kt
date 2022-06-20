package com.example.githubuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.githubuser.databinding.ItemListUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listUser = ArrayList<ItemsItem>()

    fun setList(user: ArrayList<ItemsItem>) {
        listUser.clear()
        listUser.addAll(user)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(var binding: ItemListUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemsItem)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = listUser[position]
        holder.binding.rvName.text = user.login
        Glide.with(holder.itemView.context)
            .load(user.avatarUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.binding.rvAvatar)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}