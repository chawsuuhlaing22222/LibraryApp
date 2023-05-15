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

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var progress = 0
    private val path = Path()
    private var borderWidth = 40f
    private var cornerRadius = 0f


    init {
        paint.isAntiAlias = true
        paint.strokeWidth = borderWidth
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
        /*paint.style=Paint.Style.FILL_AND_STROKE
        paint.color=resources.getColor(R.color.colorSecondaryText, null)
        paint.strokeWidth = borderWidth/2
        paint.strokeCap=Paint.Cap.ROUND
        canvas?.drawLine(0f,borderWidth,width.toFloat(),borderWidth,paint)

        var progressF=(progress.toFloat()/100)*width
        paint.color=resources.getColor(R.color.colorAccent, null)
        paint.strokeCap=Paint.Cap.ROUND
       paint.style=Paint.Style.FILL_AND_STROKE
        canvas?.drawLine(0f,borderWidth,progressF,borderWidth,paint)*/

        paint.style=Paint.Style.FILL_AND_STROKE
        paint.color=resources.getColor(R.color.colorSecondaryText, null)
        paint.strokeWidth = 25f
        paint.strokeCap=Paint.Cap.ROUND
        canvas?.drawLine(40f, 50f, 200f, 50f, paint);

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }


}