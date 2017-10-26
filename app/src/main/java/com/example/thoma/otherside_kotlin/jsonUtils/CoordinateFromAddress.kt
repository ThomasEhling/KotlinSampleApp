package com.example.thoma.otherside_kotlin.jsonUtils

import android.os.AsyncTask
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class CoordinateFromAddress : AsyncTask<String, Void, String>() {

    private var onComplete: OnTaskCompleted? = null

    interface OnTaskCompleted {
        fun onTaskCompleted(url: String?)
        fun onError()
    }

    fun onFinish(onComplete: OnTaskCompleted) {
        this.onComplete = onComplete
    }

    override fun doInBackground(vararg url: String): String? {

        val response: Response
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url[0])
                .build()
        try {
            response = client.newCall(request).execute()
            Log.v("URL", request.toString())
            return response.body()?.string()

        } catch (e: IOException) {
            e.printStackTrace()
            onComplete!!.onError()
        }

        return null
    }

    override fun onPostExecute(result: String) {
        try {
            onComplete!!.onTaskCompleted(result)
        } catch (e: Exception) {
            e.printStackTrace()
            onComplete!!.onError()
        }
    }

}

