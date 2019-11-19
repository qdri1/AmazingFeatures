package com.example.amazingfeatures.universal.extensions

class Extensions {

    fun String.hasSpaces() = find { it == ' ' } != null


}

class AquariumPlant(val color: String, val size: Int)

fun AquariumPlant.isRed() = color == "red"    // OK
fun AquariumPlant.isBig() = size > 50         // gives error
val AquariumPlant.isGreen: Boolean
    get() = color == "green"

fun AquariumPlant?.pull() {
    this?.apply {
        println("###pull plant")
    }
}