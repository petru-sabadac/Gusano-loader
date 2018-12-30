package com.sabadac.gusanoloader

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class GusanoLoader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val canvasSide = dpToPx(500, context)
    private val squareSide = 40
    private val squareDistance = 8
    private val squareCornerRadius = 10

    private val bitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = Bitmap.createBitmap(canvasSide.toInt(), canvasSide.toInt(), Bitmap.Config.ARGB_8888)
    private val bitmapCanvas = Canvas(bitmap)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var buttonRectF = RectF()

    init {
        paint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        bitmapCanvas.save()
        bitmapCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        bitmapCanvas.rotate(45f, bitmap.width / 2f, bitmap.height / 2f)

        paint.color = Color.TRANSPARENT
        buttonRectF.left = bitmap.width / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        buttonRectF.top = bitmap.height / 2f - dpToPx(squareSide, context) - dpToPx(squareDistance, context) -
                dpToPx(squareSide / 2, context)
        buttonRectF.right = bitmap.width / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)
        buttonRectF.bottom = bitmap.height / 2f - dpToPx(squareDistance, context) - dpToPx(squareSide / 2, context)
        bitmapCanvas.drawRoundRect(
            buttonRectF,
            dpToPx(squareCornerRadius / 2, context),
            dpToPx(squareCornerRadius / 2, context),
            paint
        )

        paint.color = Color.RED
        buttonRectF.left = bitmap.width / 2f - dpToPx(squareSide / 2, context)
        buttonRectF.top = bitmap.height / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        buttonRectF.right = bitmap.width / 2f + dpToPx(squareSide / 2, context)
        buttonRectF.bottom = bitmap.height / 2f - dpToPx(squareDistance, context) - dpToPx(squareSide / 2, context)
        bitmapCanvas.drawRoundRect(
            buttonRectF,
            dpToPx(squareCornerRadius / 2, context),
            dpToPx(squareCornerRadius / 2, context),
            paint
        )

        paint.color = Color.RED
        buttonRectF.left = bitmap.width / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        buttonRectF.top = bitmap.height / 2f - dpToPx(squareSide, context) - dpToPx(squareDistance, context) -
                dpToPx(squareSide / 2, context)
        buttonRectF.right = bitmap.width / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context) +
                dpToPx(squareSide, context)
        buttonRectF.bottom = bitmap.height / 2f - dpToPx(squareDistance, context) - dpToPx(squareSide / 2, context)
        bitmapCanvas.drawRoundRect(
            buttonRectF,
            dpToPx(squareCornerRadius / 2, context),
            dpToPx(squareCornerRadius / 2, context),
            paint
        )

        paint.color = Color.RED
        buttonRectF.left = bitmap.width / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        buttonRectF.top = bitmap.height / 2f - dpToPx(squareSide / 2, context)
        buttonRectF.right = bitmap.width / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)
        buttonRectF.bottom = bitmap.height / 2f + dpToPx(squareSide / 2, context)
        bitmapCanvas.drawRoundRect(
            buttonRectF,
            dpToPx(squareCornerRadius / 2, context),
            dpToPx(squareCornerRadius / 2, context),
            paint
        )

        paint.color = Color.RED
        buttonRectF.left = (bitmap.width - dpToPx(squareSide, context)) / 2f
        buttonRectF.top = (bitmap.height - dpToPx(squareSide, context)) / 2f
        buttonRectF.right = (bitmap.width + dpToPx(squareSide, context)) / 2f
        buttonRectF.bottom = (bitmap.height + dpToPx(squareSide, context)) / 2f
        bitmapCanvas.drawRoundRect(
            buttonRectF,
            dpToPx(squareCornerRadius / 2, context),
            dpToPx(squareCornerRadius / 2, context),
            paint
        )

        paint.color = Color.RED
        buttonRectF.left = bitmap.width / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        buttonRectF.top = bitmap.height / 2f - dpToPx(squareSide / 2, context)
        buttonRectF.right = bitmap.width / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context) +
                dpToPx(squareSide, context)
        buttonRectF.bottom = bitmap.height / 2f + dpToPx(squareSide / 2, context)
        bitmapCanvas.drawRoundRect(
            buttonRectF,
            dpToPx(squareCornerRadius / 2, context),
            dpToPx(squareCornerRadius / 2, context),
            paint
        )

        paint.color = Color.RED
        buttonRectF.left = bitmap.width / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        buttonRectF.top = bitmap.height / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        buttonRectF.right = bitmap.width / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)
        buttonRectF.bottom = bitmap.height / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context) +
                dpToPx(squareSide, context)
        bitmapCanvas.drawRoundRect(
            buttonRectF,
            dpToPx(squareCornerRadius / 2, context),
            dpToPx(squareCornerRadius / 2, context),
            paint
        )

        paint.color = Color.RED
        buttonRectF.left = (bitmap.width - dpToPx(squareSide, context)) / 2f
        buttonRectF.top = bitmap.height / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        buttonRectF.right = (bitmap.width + dpToPx(squareSide, context)) / 2f
        buttonRectF.bottom = bitmap.height / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context) +
                dpToPx(squareSide, context)
        bitmapCanvas.drawRoundRect(
            buttonRectF,
            dpToPx(squareCornerRadius / 2, context),
            dpToPx(squareCornerRadius / 2, context),
            paint
        )

        paint.color = Color.TRANSPARENT
        buttonRectF.left = bitmap.width / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        buttonRectF.top = bitmap.height / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        buttonRectF.right = bitmap.width / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context) +
                dpToPx(squareSide, context)
        buttonRectF.bottom = bitmap.height / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context) +
                dpToPx(squareSide, context)
        bitmapCanvas.drawRoundRect(
            buttonRectF,
            dpToPx(squareCornerRadius / 2, context),
            dpToPx(squareCornerRadius / 2, context),
            paint
        )

        canvas?.drawBitmap(bitmap, (width - bitmap.width) / 2f, (height - bitmap.height) / 2f, bitmapPaint)

        bitmapCanvas.restore()
    }

    private fun dpToPx(dp: Int, context: Context): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics)
}