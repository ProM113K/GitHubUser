package com.example.githubuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name: String,
    var username: String,
    var location: String,
    var repository: String,
    var imgAvatar: String
) : Parcelable
