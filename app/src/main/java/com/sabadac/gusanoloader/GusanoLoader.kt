package com.sabadac.gusanoloader

import android.animation.*
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.support.v4.view.animation.PathInterpolatorCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class GusanoLoader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val squareSide = 40
    private val canvasSide = dpToPx(squareSide * 5)
    private val squareDistance = 8
    private val squareCornerRadius = 10
    private val delay = 150L
    private val bitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = Bitmap.createBitmap(canvasSide.toInt(), canvasSide.toInt(), Bitmap.Config.ARGB_8888)
    private val bitmapCanvas = Canvas(bitmap)
    private val duration = 300L
    private val colorTransitionDuration = 5000L
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val positions = Array(9) { RectF() }
    private val squares = Array(7) { RectF() }
    private val animatorSet = AnimatorSet()
    private val colorAnimator = ValueAnimator.ofObject(
        ArgbEvaluator(),
        ContextCompat.getColor(context, R.color.squareFirstColor),
        ContextCompat.getColor(context, R.color.squareSecondColor),
        ContextCompat.getColor(context, R.color.squareThirdColor)
    )

    init {
        initPositions()
        restartSquaresPositions()
        playAnimations()
    }

    private fun playAnimations() {
        animatorSet.playTogether(
            moveFromTo(1, 2),
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

        colorAnimator.duration = colorTransitionDuration
        colorAnimator.repeatCount = ValueAnimator.INFINITE
        colorAnimator.repeatMode = ValueAnimator.REVERSE
        colorAnimator.setEvaluator(ArgbEvaluator())
        colorAnimator.addUpdateListener { animation ->
            paint.color = animation.animatedValue as Int
            invalidate()
        }
        colorAnimator.start()
    }

    override fun clearAnimation() {
        colorAnimator.removeAllUpdateListeners()
        colorAnimator.cancel()

        animatorSet.removeAllListeners()
        animatorSet.cancel()
        super.clearAnimation()
    }

    private fun moveFromTo(from: Int, to: Int, withDelay: Long = 0L): ValueAnimator {
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.duration = duration
        valueAnimator.startDelay = withDelay
        valueAnimator.interpolator = PathInterpolatorCompat.create(0.42f, 0.0f, 0.58f, 1.0f)
        valueAnimator.addUpdateListener { animation ->
            val fraction = animation.animatedFraction
            val index = if (from < 2) from else from - 1
            squares[index].left = positions[from].left + fraction * (positions[to].left - positions[from].left)
            squares[index].right = positions[from].right + fraction * (positions[to].right - positions[from].right)
            squares[index].top = positions[from].top + fraction * (positions[to].top - positions[from].top)
            squares[index].bottom = positions[from].bottom + fraction *
                    (positions[to].bottom - positions[from].bottom)
            invalidate()
        }
        return valueAnimator
    }

    private fun initPositions() {
        positions[0].left = (bitmap.width - dpToPx(squareSide)) / 2f - dpToPx(squareSide) -
                dpToPx(squareDistance)
        positions[0].top = (bitmap.height - dpToPx(squareSide)) / 2f - dpToPx(squareSide) -
                dpToPx(squareDistance)
        positions[0].right = (bitmap.width - dpToPx(squareSide)) / 2f - dpToPx(squareDistance)
        positions[0].bottom = (bitmap.height - dpToPx(squareSide)) / 2f - dpToPx(squareDistance)

        positions[1].left = (bitmap.width - dpToPx(squareSide)) / 2f
        positions[1].top = (bitmap.height - dpToPx(squareSide)) / 2f - dpToPx(squareSide) -
                dpToPx(squareDistance)
        positions[1].right = (bitmap.width + dpToPx(squareSide)) / 2f
        positions[1].bottom = (bitmap.height - dpToPx(squareSide)) / 2f - dpToPx(squareDistance)

        positions[2].left = (bitmap.width + dpToPx(squareSide)) / 2f + dpToPx(squareDistance)
        positions[2].top = (bitmap.height - dpToPx(squareSide)) / 2f - dpToPx(squareSide) -
                dpToPx(squareDistance)
        positions[2].right = (bitmap.width + dpToPx(squareSide)) / 2f + dpToPx(squareSide) +
                dpToPx(squareDistance)
        positions[2].bottom = (bitmap.height - dpToPx(squareSide)) / 2f - dpToPx(squareDistance)

        positions[3].left = (bitmap.width - dpToPx(squareSide)) / 2f - dpToPx(squareSide) -
                dpToPx(squareDistance)
        positions[3].top = (bitmap.height - dpToPx(squareSide)) / 2f
        positions[3].right = (bitmap.width - dpToPx(squareSide)) / 2f - dpToPx(squareDistance)
        positions[3].bottom = (bitmap.height + dpToPx(squareSide)) / 2f

        positions[4].left = (bitmap.width - dpToPx(squareSide)) / 2f
        positions[4].top = (bitmap.height - dpToPx(squareSide)) / 2f
        positions[4].right = (bitmap.width + dpToPx(squareSide)) / 2f
        positions[4].bottom = (bitmap.height + dpToPx(squareSide)) / 2f

        positions[5].left = (bitmap.width + dpToPx(squareSide)) / 2f + dpToPx(squareDistance)
        positions[5].top = (bitmap.height - dpToPx(squareSide)) / 2f
        positions[5].right = (bitmap.width + dpToPx(squareSide)) / 2f + dpToPx(squareSide) +
                dpToPx(squareDistance)
        positions[5].bottom = (bitmap.height + dpToPx(squareSide)) / 2f

        positions[6].left = (bitmap.width - dpToPx(squareSide)) / 2f - dpToPx(squareSide) -
                dpToPx(squareDistance)
        positions[6].top = (bitmap.height + dpToPx(squareSide)) / 2f + dpToPx(squareDistance)
        positions[6].right = (bitmap.width - dpToPx(squareSide)) / 2f - dpToPx(squareDistance)
        positions[6].bottom = (bitmap.height + dpToPx(squareSide)) / 2f + dpToPx(squareSide) +
                dpToPx(squareDistance)

        positions[7].left = (bitmap.width - dpToPx(squareSide)) / 2f
        positions[7].top = (bitmap.height + dpToPx(squareSide)) / 2f + dpToPx(squareDistance)
        positions[7].right = (bitmap.width + dpToPx(squareSide)) / 2f
        positions[7].bottom = (bitmap.height + dpToPx(squareSide)) / 2f + dpToPx(squareSide) +
                dpToPx(squareDistance)

        positions[8].left = (bitmap.width + dpToPx(squareSide)) / 2f + dpToPx(squareDistance)
        positions[8].top = (bitmap.height + dpToPx(squareSide)) / 2f + dpToPx(squareDistance)
        positions[8].right = (bitmap.width + dpToPx(squareSide)) / 2f + dpToPx(squareSide) +
                dpToPx(squareDistance)
        positions[8].bottom = (bitmap.height + dpToPx(squareSide)) / 2f + dpToPx(squareSide) +
                dpToPx(squareDistance)
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

        squares.forEach { position ->

            bitmapCanvas.drawRoundRect(
                position,
                dpToPx(squareCornerRadius / 2),
                dpToPx(squareCornerRadius / 2),
                paint
            )
        }

        canvas?.drawBitmap(bitmap, (width - bitmap.width) / 2f, (height - bitmap.height) / 2f, bitmapPaint)

        bitmapCanvas.restore()
    }

    private fun dpToPx(dp: Int): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), Resources.getSystem().displayMetrics)
}