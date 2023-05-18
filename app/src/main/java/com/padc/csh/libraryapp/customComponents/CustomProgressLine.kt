package com.padc.csh.libraryapp.customComponents

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.setBlendMode
import com.padc.csh.libraryapp.R

class CustomProgressLine @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    //size in pixel
    private var size = 0

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var progress = 0
    private val path = Path()
    private var borderWidth = 40f
    private var cornerRadius = 0f


    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = resources.getColor(R.color.colorSecondaryText, null)

        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressLine)
            if (a.hasValue(R.styleable.CustomProgressLine_progress)) {
                progress = a.getInteger(R.styleable.CustomProgressLine_progress, 0)
           }

           /* if (a.hasValue(R.styleable.CustomProgressLine_borderWidthProgressLine)) {
                borderWidth = a.getDimension(R.styleable.CustomProgressLine_borderWidthProgressLine, 4f)
            }*/
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.style=Paint.Style.FILL_AND_STROKE
        paint.color=resources.getColor(R.color.colorSecondaryText, null)
        paint.strokeWidth = 15f
        paint.strokeCap=Paint.Cap.ROUND
        canvas?.drawLine(40f, 30f, 500f, 30f, paint);

        paint.style=Paint.Style.FILL_AND_STROKE
        paint.color=resources.getColor(R.color.colorAccent, null)
        paint.strokeWidth = 15f
       var progressF =(progress.toFloat()/100)*width
        paint.strokeCap=Paint.Cap.ROUND
        canvas?.drawLine(40f, 30f, progressF, 30f, paint);

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = measuredWidth.coerceAtMost(measuredHeight)
        setMeasuredDimension(size, size)
    }


}