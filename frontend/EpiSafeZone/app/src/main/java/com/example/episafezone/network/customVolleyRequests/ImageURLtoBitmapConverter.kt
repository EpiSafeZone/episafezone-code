package com.example.episafezone.network.customVolleyRequests

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.BuildConfig
import com.example.episafezone.MainActivity
import com.example.episafezone.models.Patient

/**
 * Class designed to create custom volley requests to obtain images from a URL.
 */
object  ImageURLtoBitmapConverter {
    private val API_IP = BuildConfig.API_LINK

    private val url = "http://$API_IP"

    // TODO: añadir funciones que falten y corregir metodo comentado.
    fun downloadImage(view : View, patient : Patient): Bitmap? {

        val urlC = "$url/image/${patient.id}"
        val requestQueue = Volley.newRequestQueue(MainActivity.getContext())
        var bitmap: Bitmap? = null

        val imageRequest = ImageRequest(
            urlC,
            { response -> bitmap = response
                Log.i("imageBitmap", bitmap.toString())
                //ProfileLogic.setImage(bitmap, view)
            },
            143,
            143,
            ImageView.ScaleType.CENTER_CROP,
            Bitmap.Config.RGB_565,
            { error -> Log.e("ImageLoadError", "Error listener: $error")
                //ProfileLogic.setImage(null, view)
        })

        requestQueue.add(imageRequest)

        return bitmap
    }
}