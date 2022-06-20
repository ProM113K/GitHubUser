package com.example.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.example.githubuser.ItemsItem
import com.example.githubuser.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(LayoutInflater.from(this@UserDetailActivity))
        setContentView(binding.root)

        val githubUser = intent.getParcelableExtra<ItemsItem>(EXTRA_DATA) as ItemsItem
        binding.tvName.text = githubUser.login
        binding.tvLocation.text = githubUser.login
        binding.tvRepository.text = githubUser.login
        binding.tvUsername.text = githubUser.login
        Glide.with(this@UserDetailActivity)
            .load(githubUser.avatarUrl)
            .circleCrop()
            .into(binding.imgAvatar)
    }
}