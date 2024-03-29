package com.sasfmlzr.mathoptimization.custom_view

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.sasfmlzr.mathoptimization.databinding.ViewEditTextBinding
import kotlinx.android.synthetic.main.view_edit_text.view.*

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
        binder.errorIcon.visibility = View.INVISIBLE
        binder.errorText.visibility = View.INVISIBLE

        binder.editText.isError = false
        binder.editText.refreshDrawableState()
    }

    fun setInputType(inputType: Int) {
        binder.editText.inputType = inputType
    }

    val text: Editable
        get() = edit_text.text
}
