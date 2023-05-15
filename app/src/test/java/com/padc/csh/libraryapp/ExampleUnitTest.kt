package com.padc.csh.libraryapp

import com.padc.csh.libraryapp.activities.MainActivity
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val activityRes= MainActivity::class.java
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}