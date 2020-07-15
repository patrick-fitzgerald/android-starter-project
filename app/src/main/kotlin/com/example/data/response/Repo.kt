package com.example.data.response

import com.google.gson.annotations.SerializedName

data class Repo(

    @SerializedName("id")
    val id: String = "",

    @SerializedName("description")
    val description: String = ""

)
