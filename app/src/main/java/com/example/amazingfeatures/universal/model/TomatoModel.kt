package com.example.amazingfeatures.universal.model

import com.example.amazingfeatures.universal._abstract.OnClickModel

class TomatoModel(
    val str1: String,
    override var onClick: (Int) -> Unit = {}
) : OnClickModel()