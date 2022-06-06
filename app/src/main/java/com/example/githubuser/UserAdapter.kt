package com.example.githubuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ItemListUserBinding

class UserAdapter(private val listUser: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(var binding: ItemListUserBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = listUser[position]
        holder.binding.rvName.text = user.name
        holder.binding.rvUsername.text = user.username
        Glide.with(holder.itemView.context)
            .load(user.imgAvatar)
            .circleCrop()
            .into(holder.binding.rvAvatar)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}