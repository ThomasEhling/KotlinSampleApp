package com.example.thoma.otherside_kotlin.jsonUtils

import android.util.Log
import com.example.thoma.otherside_kotlin.model.address.*
import com.google.gson.GsonBuilder

class AddressMapper {
    companion object {

        fun parseYtVideos(result: String): AddressModel {

            val address = AddressModel()

            val gson = GsonBuilder().create()
            val response = gson.fromJson(result, Main_Address::class.java)

            address.status = response.status

            if (address.status == "ZERO_RESULTS") {
                address.isInTheSea = true;
                Log.i("AddressMapper", "Address in the sea")
                return address;
            } else if (address.status == "OK") {

                val result: Result = response.results!!.get(0)

                address.addressFormatted = result.formattedAddress

                val coordinate: Location = result.geometry!!.location

                address.latitude = coordinate.lat
                address.longitude = coordinate.lng

                val addressDetails: List<AddressComponent> = result.addressComponents!!;

                for (item in addressDetails) {
                    when (item.types.get(0)) {
                        "locality" -> address.town = item.longName
                        "country" -> address.country = item.longName
                    }
                }

                Log.i("AddressMapper", "Address data mapped correctly!")
            }

            Log.i("AddressMapper", "Error occured, check satus")

            return address

        }
    }

}