package com.example.amazingfeatures.universal.model

import com.example.amazingfeatures.universal._abstract.OnClickModel

class NouteModel(
    val str1: String,
    val str2: String,
    override var onClick: (Int) -> Unit = {}
) : OnClickModel()