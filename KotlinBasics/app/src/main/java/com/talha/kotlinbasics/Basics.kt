package com.talha.kotlinbasics

fun main() {
    val myName = "Denis"
    var myAge = 31

    // Integer TYPES: Byte (8 bit), Short (16 bit), Int (32 bit), Long (64 bit)
    val myByte1: Byte = 13
    val myShort1: Short = 125
    val myInt123: Int = 123123123
    val myLong1: Long = 12_039_812_309_487_120

    // Floating Point number Types: Float (32 bit), Double (64 bit)
    val myFloat1234: Float = 13.37F
    val myDouble1234: Double = 3.14159265358979323846

    // Booleans the type Boolean is used to represent logical values.
    // It can have two possible values true and false.
    var isSunny: Boolean = true
    isSunny = false

    // Characters
    val letterChar = 'A'
    val digitChar = '1'

    // Strings
    val myStr = "Hello World"
    val firCharInStr = myStr[0]
    val lastCharInStr = myStr[myStr.length - 1]

    /*
    Please write a program in which you are assigning those values to variables with the right (specific) data type:
    "Android Masterclass"
    13.37F
    3.14159265358979
    25
    2020
    18881234567
    true
    'a'

    Please don't simply use Kotlin's powerful type inference feature for this.
     */

    val courseName = "Android Masterclass"
    println(courseName)

    val myFloat = 13.37F
    println(myFloat)

    val myDouble = 3.14159265358979
    println(myDouble)

    val myByte: Byte = 25
    println(myByte)

    val myShort: Short = 2020
    println(myShort)

    val myInt: Long = 18881234567
    println(myInt)

    val myBoolean = true
    println(myBoolean)

    val myChar = 'a'
    println(myChar)

}