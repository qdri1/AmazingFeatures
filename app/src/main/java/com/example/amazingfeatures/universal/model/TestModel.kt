package com.example.amazingfeatures.universal.model

import com.example.amazingfeatures.universal._abstract.OnClickModel


class TestModel(
    val str1: String,
    val str2: String,
    val str3: String,
    val img: String, override var onClick: (Int) -> Unit = {}
) : OnClickModel()