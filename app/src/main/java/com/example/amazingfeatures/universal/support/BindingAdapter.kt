package com.example.amazingfeatures.universal.support

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.amazingfeatures.R

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("kudri:src")
    fun setImage(view: ImageView, url: String) {
        if (url.isBlank()) {
            view.setImageResource(R.drawable.rahmet_logo)
        } else {
            Glide.with(view.context)
                .load(url)
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter(
        value = ["kudri:text", "kudri:startString", "kudri:endString"],
        requireAll = false
    )
    fun setText(view: TextView, text: String, startString: String?, endString: String?) {
        val strStart = startString ?: ""
        val strEnd = endString ?: ""
        val textValue = "$strStart $text $strEnd"
        view.text = textValue.trim()
    }

}