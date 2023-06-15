package com.example.mnotes.ui.customui

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.mnotes.R

/** Raihan Chaira on 5/7/2023
 * raihanchaira21@gmail.com
 */
class MyEditText : AppCompatEditText, View.OnTouchListener {

    private lateinit var clearButtonImage: Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = ""

        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun init() {
        clearButtonImage =
            ContextCompat.getDrawable(context, R.drawable.baseline_close_24) as Drawable

        setOnTouchListener(this)

        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadius = 30f
        shape.setColor(ContextCompat.getColor(context, R.color.gray))
        background = shape


        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val passLog = R.id.ed_login_pass
                val passReg = R.id.ed_regis_pass
                val emailLog = R.id.ed_login_name
                val emailReg = R.id.ed_regis_email
//                if (id == passLog && s.toString().length < 8 || id == passReg && s.toString().length < 8) {
//                    error = "Password must be at least 8 characters"
////                } else if (id == emailLog && !Patterns.EMAIL_ADDRESS.matcher(s.toString())
////                        .matches() || id == emailReg && !Patterns.EMAIL_ADDRESS.matcher(
////                        s.toString()
////                    ).matches()
////                ) {
//                    error = "Invalid email format"
//                } else {
//                    error = null
//                }

                if (s.toString().isNotEmpty() && error.isNullOrEmpty()) {
                    showClearButton()
                } else {
                    hideClearButton()
                }
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
    }

    fun hasError(): Boolean {
        return error != null
    }

    private fun showClearButton() {
        setButtonDrawables(endOfTheText = clearButtonImage)
    }

    private fun hideClearButton() {
        setButtonDrawables()
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val clearButtonStart: Float
            val clearButtonEnd: Float
            var isClearButtonClicked = false

            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                clearButtonEnd = (clearButtonImage.intrinsicWidth + paddingStart).toFloat()
                when {
                    event.x < clearButtonEnd -> isClearButtonClicked = true
                }
            } else {
                clearButtonStart = (width - paddingEnd - clearButtonImage.intrinsicWidth).toFloat()
                when {
                    event.x > clearButtonStart -> isClearButtonClicked = true
                }
            }
            if (isClearButtonClicked) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        clearButtonImage = ContextCompat.getDrawable(
                            context,
                            R.drawable.baseline_close_24
                        ) as Drawable
                        showClearButton()
                        return true
                    }

                    MotionEvent.ACTION_UP -> {
                        clearButtonImage = ContextCompat.getDrawable(
                            context,
                            R.drawable.baseline_close_24
                        ) as Drawable
                        when {
                            text != null -> text?.clear()
                        }
                        hideClearButton()
                        return true
                    }

                    else -> return false
                }
            } else return false
        }
        return false
    }
}