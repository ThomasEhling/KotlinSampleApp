package com.example.thoma.otherside_kotlin.model.address


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main_Address {

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null
    @SerializedName("status")
    @Expose
    var status: String? = null

}