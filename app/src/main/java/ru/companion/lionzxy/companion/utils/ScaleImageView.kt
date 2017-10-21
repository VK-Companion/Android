package ru.companion.lionzxy.companion.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.widget.ImageView

class ScaleImageView : ImageView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun setImageBitmap(bm: Bitmap?) {
        if (bm == null) {
            return super.setImageBitmap(bm)
        }
        val heightView = height.toFloat()
        val widthView = width.toFloat()

        val heightBitmap = bm.height.toFloat()
        val widthBitmap = bm.width.toFloat()

        if (heightView / widthView > heightBitmap / widthBitmap) {
            val newWidth = (heightBitmap * widthView) / heightView

            val offsetWidth = ((widthBitmap - newWidth) / 2).toInt()

            val newBitmap = Bitmap.createBitmap(bm, offsetWidth,
                    0, newWidth.toInt(), heightBitmap.toInt())
            super.setImageBitmap(newBitmap)
        } else {
            super.setImageBitmap(bm)
        }
    }
}