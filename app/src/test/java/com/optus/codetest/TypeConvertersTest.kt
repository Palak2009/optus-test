package com.optus.codetest

import com.optus.codetest.database.MyTypeConverters
import com.optus.codetest.utils.getMockAddress1
import com.optus.codetest.utils.getMockCompany1
import com.google.gson.Gson
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TypeConvertersTest {

    private val addressJson = Gson().toJson(getMockAddress1())
    private val companyJson = Gson().toJson(getMockCompany1())

    @Test
    fun stringToAddressTest() {
        assertThat(getMockAddress1(), `is`(MyTypeConverters().stringToAddress(addressJson)))
    }

    @Test
    fun addressToStringTest() {
        assertThat(addressJson.toString(), `is`(MyTypeConverters().addressToString(getMockAddress1())))
    }

    @Test
    fun stringToCompanyTest() {
        assertThat(getMockCompany1(), `is`(MyTypeConverters().stringToCompany(companyJson.toString())))
    }

    @Test
    fun companyToStringTest() {
        assertThat(companyJson.toString(), `is`(MyTypeConverters().companyToString(getMockCompany1())))
    }

}