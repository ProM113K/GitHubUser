package com.example.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.bumptech.glide.Glide
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

        val githubUser = intent.getParcelableExtra<User>(EXTRA_DATA) as User
        binding.tvName.text = githubUser.name
        binding.tvLocation.text = githubUser.location
        binding.tvRepository.text = githubUser.repository
        binding.tvUsername.text = githubUser.username
        Glide.with(this@UserDetailActivity)
            .load(githubUser.imgAvatar)
            .circleCrop()
            .into(binding.imgAvatar)
    }
}