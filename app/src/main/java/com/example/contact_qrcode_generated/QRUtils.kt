package com.example.contact_qrcode_generated

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

fun generateQRCode(data1: String, data2: String, data3: String, data4: String): Bitmap? {
    val combinedData = "$data1\n$data2\n$data3\n$data4"
    return try {
        val bitMatrix: BitMatrix = MultiFormatWriter().encode(
            combinedData,
            BarcodeFormat.QR_CODE,
            500,
            500
        )
        val barcodeEncoder = BarcodeEncoder()
        barcodeEncoder.createBitmap(bitMatrix)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

