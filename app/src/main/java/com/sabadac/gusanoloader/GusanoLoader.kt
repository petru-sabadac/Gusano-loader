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
    private val positions = Array(9) { RectF() }


    init {
        positions[0].left = -dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        positions[0].top = -dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        positions[0].right = -dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)
        positions[0].bottom = -dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)

        positions[1].left = -dpToPx(squareSide / 2, context)
        positions[1].top = -dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        positions[1].right = dpToPx(squareSide / 2, context)
        positions[1].bottom = -dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)

        positions[2].left = dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        positions[2].top = -dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        positions[2].right = dpToPx(squareSide / 2, context) + dpToPx(squareSide, context) +
                dpToPx(squareDistance, context)
        positions[2].bottom = -dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)

        positions[3].left = -dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        positions[3].top = -dpToPx(squareSide / 2, context)
        positions[3].right = -dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)
        positions[3].bottom = dpToPx(squareSide / 2, context)

        positions[4].left = -dpToPx(squareSide / 2, context)
        positions[4].top = -dpToPx(squareSide / 2, context)
        positions[4].right = dpToPx(squareSide / 2, context)
        positions[4].bottom = dpToPx(squareSide / 2, context)

        positions[5].left = dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        positions[5].top = -dpToPx(squareSide / 2, context)
        positions[5].right = dpToPx(squareSide / 2, context) + dpToPx(squareSide, context) +
                dpToPx(squareDistance, context)
        positions[5].bottom = dpToPx(squareSide / 2, context)

        positions[6].left = -dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        positions[6].top = dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        positions[6].right = -dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)
        positions[6].bottom = dpToPx(squareSide / 2, context) + dpToPx(squareSide, context) +
                dpToPx(squareDistance, context)

        positions[7].left = -dpToPx(squareSide / 2, context)
        positions[7].top = dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        positions[7].right = dpToPx(squareSide / 2, context)
        positions[7].bottom = dpToPx(squareSide / 2, context) + dpToPx(squareSide, context) +
                dpToPx(squareDistance, context)

        positions[8].left = dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        positions[8].top = dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        positions[8].right = dpToPx(squareSide / 2, context) + dpToPx(squareSide, context) +
                dpToPx(squareDistance, context)
        positions[8].bottom = dpToPx(squareSide / 2, context) + dpToPx(squareSide, context) +
                dpToPx(squareDistance, context)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        bitmapCanvas.save()
        bitmapCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
//        bitmapCanvas.rotate(45f, bitmap.width / 2f, bitmap.height / 2f)

        positions.forEachIndexed { index, position ->
            if (index == 0 || index == 8) {
                paint.color = Color.TRANSPARENT
            } else {
                paint.color = Color.RED
            }

            position.left = bitmap.width / 2f + position.left
            position.top = bitmap.height / 2f + position.top
            position.right = bitmap.width / 2f + position.right
            position.bottom = bitmap.height / 2f + position.bottom

            bitmapCanvas.drawRoundRect(
                position,
                dpToPx(squareCornerRadius / 2, context),
                dpToPx(squareCornerRadius / 2, context),
                paint
            )
        }

        canvas?.drawBitmap(bitmap, (width - bitmap.width) / 2f, (height - bitmap.height) / 2f, bitmapPaint)

        bitmapCanvas.restore()
    }

    private fun dpToPx(dp: Int, context: Context): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics)
}