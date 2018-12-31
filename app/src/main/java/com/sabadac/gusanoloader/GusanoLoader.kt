package com.sabadac.gusanoloader

import android.animation.*
import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.DecelerateInterpolator

class GusanoLoader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val canvasSide = dpToPx(500, context)
    private val squareSide = 40
    private val squareDistance = 8
    private val squareCornerRadius = 10
    private val delay = 150L
    private val bitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = Bitmap.createBitmap(canvasSide.toInt(), canvasSide.toInt(), Bitmap.Config.ARGB_8888)
    private val bitmapCanvas = Canvas(bitmap)
    private val duration = 300L
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val positions = Array(9) { RectF() }
    private val squares = Array(7) { RectF() }

    init {
        initPositions()
        restartSquaresPositions()
        playAnimations()
    }

    private fun playAnimations() {
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            moveFromTo(1, 2, 0L),
            moveFromTo(0, 1, 1 * duration - 1 * delay),
            moveFromTo(3, 0, 2 * duration - 2 * delay),
            moveFromTo(6, 3, 3 * duration - 3 * delay),
            moveFromTo(7, 6, 4 * duration - 4 * delay),
            moveFromTo(4, 7, 5 * duration - 5 * delay),
            moveFromTo(5, 4, 6 * duration - 6 * delay),
            moveFromTo(2, 5, 7 * duration - 7 * delay)
        )

        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                restartSquaresPositions()
                animatorSet.start()
            }
        })
        animatorSet.start()

        val colorAnimator = ValueAnimator.ofObject(
            ArgbEvaluator(),
            ContextCompat.getColor(context, R.color.squareFirstColor),
            ContextCompat.getColor(context, R.color.squareSecondColor),
            ContextCompat.getColor(context, R.color.squareThirdColor)
        )
        colorAnimator.duration = 6000
        colorAnimator.repeatCount = ValueAnimator.INFINITE
        colorAnimator.repeatMode = ValueAnimator.REVERSE
        colorAnimator.setEvaluator(ArgbEvaluator())
        colorAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                paint.color = animation?.animatedValue as Int
                invalidate()
            }
        })
        colorAnimator.start()
    }

    private fun moveFromTo(from: Int, to: Int, withDelay: Long): ValueAnimator {
        val leftProperty = "left"
        val topProperty = "top"
        val rightProperty = "right"
        val bottomProperty = "bottom"
        val leftValueHolder = PropertyValuesHolder.ofFloat(leftProperty, positions[from].left, positions[to].left)
        val topValueHolder = PropertyValuesHolder.ofFloat(topProperty, positions[from].top, positions[to].top)
        val rightValueHolder = PropertyValuesHolder.ofFloat(rightProperty, positions[from].right, positions[to].right)
        val bottomValueHolder =
            PropertyValuesHolder.ofFloat(bottomProperty, positions[from].bottom, positions[to].bottom)

        val valueAnimator = ValueAnimator()
        valueAnimator.setValues(leftValueHolder, topValueHolder, rightValueHolder, bottomValueHolder)
        valueAnimator.duration = duration
        valueAnimator.startDelay = withDelay
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val index = if (from < 2) from else from - 1
                squares[index].left = animation?.getAnimatedValue(leftProperty) as Float
                squares[index].top = animation?.getAnimatedValue(topProperty) as Float
                squares[index].right = animation?.getAnimatedValue(rightProperty) as Float
                squares[index].bottom = animation?.getAnimatedValue(bottomProperty) as Float
                invalidate()
            }
        })
        return valueAnimator
    }

    private fun initPositions() {
        positions[0].left = bitmap.width / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        positions[0].top = bitmap.height / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        positions[0].right = bitmap.width / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)
        positions[0].bottom = bitmap.height / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)

        positions[1].left = bitmap.width / 2f - dpToPx(squareSide / 2, context)
        positions[1].top = bitmap.height / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        positions[1].right = bitmap.width / 2f + dpToPx(squareSide / 2, context)
        positions[1].bottom = bitmap.height / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)

        positions[2].left = bitmap.width / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        positions[2].top = bitmap.height / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        positions[2].right = bitmap.width / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareSide, context) +
                dpToPx(squareDistance, context)
        positions[2].bottom = bitmap.height / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)

        positions[3].left = bitmap.width / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        positions[3].top = bitmap.height / 2f - dpToPx(squareSide / 2, context)
        positions[3].right = bitmap.width / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)
        positions[3].bottom = bitmap.height / 2f + dpToPx(squareSide / 2, context)

        positions[4].left = bitmap.width / 2f - dpToPx(squareSide / 2, context)
        positions[4].top = bitmap.height / 2f - dpToPx(squareSide / 2, context)
        positions[4].right = bitmap.width / 2f + dpToPx(squareSide / 2, context)
        positions[4].bottom = bitmap.height / 2f + dpToPx(squareSide / 2, context)

        positions[5].left = bitmap.width / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        positions[5].top = bitmap.height / 2f - dpToPx(squareSide / 2, context)
        positions[5].right = bitmap.width / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareSide, context) +
                dpToPx(squareDistance, context)
        positions[5].bottom = bitmap.height / 2f + dpToPx(squareSide / 2, context)

        positions[6].left = bitmap.width / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareSide, context) -
                dpToPx(squareDistance, context)
        positions[6].top = bitmap.height / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        positions[6].right = bitmap.width / 2f - dpToPx(squareSide / 2, context) - dpToPx(squareDistance, context)
        positions[6].bottom = bitmap.height / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareSide, context) +
                dpToPx(squareDistance, context)

        positions[7].left = bitmap.width / 2f - dpToPx(squareSide / 2, context)
        positions[7].top = bitmap.height / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        positions[7].right = bitmap.width / 2f + dpToPx(squareSide / 2, context)
        positions[7].bottom = bitmap.height / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareSide, context) +
                dpToPx(squareDistance, context)

        positions[8].left = bitmap.width / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        positions[8].top = bitmap.height / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareDistance, context)
        positions[8].right = bitmap.width / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareSide, context) +
                dpToPx(squareDistance, context)
        positions[8].bottom = bitmap.height / 2f + dpToPx(squareSide / 2, context) + dpToPx(squareSide, context) +
                dpToPx(squareDistance, context)
    }

    private fun restartSquaresPositions() {
        for (index in (0..7)) {
            if (index == 2) {
                continue
            }
            if (index < 2) {
                squares[index].set(positions[index])
            } else {
                squares[index - 1].set(positions[index])
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        bitmapCanvas.save()
        bitmapCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        bitmapCanvas.rotate(45f, bitmap.width / 2f, bitmap.height / 2f)

        squares.forEachIndexed { index, position ->

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