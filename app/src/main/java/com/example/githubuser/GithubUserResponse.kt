package com.example.githubuser

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GithubUserResponse(

	@field:SerializedName("items")
	val items: ArrayList<ItemsItem>
)

@Parcelize
data class ItemsItem(

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("login")
	val login: String
) : Parcelable
