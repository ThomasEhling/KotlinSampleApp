package com.example.thoma.otherside_kotlin.model


class Tunnel(var addressOrigin: String?,
             var entrance_lat: Int, var entrance_long: Int,
             var out_lat: Int, var out_long: Int, var isFavourite: Boolean) {

    var title: String? = null //name of the Tunnel given by the User

    constructor(addressOrigin: String?) : this(addressOrigin, 0, 0, 0, 0, false) {}
}
