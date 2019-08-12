package com.szakes1.makdolannative.helpers

import org.joda.time.DateTime
import kotlin.random.Random


class Makdolan {
    fun calculateDate(): String {
        val dt = DateTime()

        val day = (dt.dayOfMonth - 1).toString().padStart(2, '0')
        val month = dt.monthOfYear.toString().padStart(2, '0')
        val year = dt.year

        return "$day/$month/$year"
    }

    fun calculateUniqueCode(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"



        val randomAnyLetter = chars.elementAt(Math.floor(Random.nextDouble() * chars.length).toInt()) // Returns random character from chars
        val randomNumber = Math.floor(Random.nextDouble() * 100).toInt() // Returns random number from 1 to 100
        val randomSmallLetter = chars.substring(26).elementAt(Math.floor(Random.nextDouble() * chars.substring(26).length).toInt()) // Returns random small character
        val randomBigLetter = chars.substring(0, 26).elementAt(Math.floor(Random.nextDouble() * chars.substring(0, 26).length).toInt()).toString() // Returns random big character
        val randomLastNumber = Math.floor(Random.nextDouble() * 10).toInt() // Returns random number from 1 to 10

        return "$randomAnyLetter-$randomNumber-${randomSmallLetter + randomBigLetter}-$randomLastNumber"
    }
}