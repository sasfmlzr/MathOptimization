package com.sasfmlzr.mathoptimization.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.sasfmlzr.mathoptimization.R
import com.sasfmlzr.mathoptimization.databinding.ViewEditTextBinding

class CustomEditTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binder: ViewEditTextBinding by lazy {
        ViewEditTextBinding.inflate(LayoutInflater.from(context))
    }

    init {
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        addView(binder.root, params)
    }

    fun showError(error: String) {
        binder.errorText.text = error
        binder.errorText.visibility = View.VISIBLE
        binder.errorIcon.visibility = View.VISIBLE

        binder.editText.isError = true
        binder.editText.refreshDrawableState()
    }

    fun hideError() {
        binder.errorIcon.visibility = View.GONE
        binder.errorText.visibility = View.GONE

        binder.editText.isError = false
        binder.editText.refreshDrawableState()
    }
}
