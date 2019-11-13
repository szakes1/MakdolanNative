package com.szakes1.makdolannative.helpers

import org.joda.time.DateTime
import kotlin.random.Random


class Makdolan {
    fun calculateDate(): String {
        val dt = DateTime()

        val day = dt.dayOfMonth
        val month = dt.monthOfYear
        val year = dt.year

        var calculatedDay: String
        var calculatedMonth: String
        var calculatedYear: String

        if (day > 1) {
            calculatedDay = (day - 1).toString().padStart(2, '0')
        }
        else {
            calculatedDay = day.toString().padStart(2, '0')
        }
        calculatedMonth = month.toString().padStart(2, '0')
        calculatedYear = year.toString()

        return "$calculatedDay/$calculatedMonth/$calculatedYear"
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