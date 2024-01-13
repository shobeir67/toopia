package com.shobeir.toopia.data.datastore

import androidx.datastore.preferences.core.*

const val USER_PREFERENCES_NAME = "login_user"

object PreferenceHelper {

    val MOBILE_NAME = stringPreferencesKey("MOBILE_NAME")
    val CITY = stringPreferencesKey("CITY")
    val FLOAT_VALUE = floatPreferencesKey("FLOAT_VALUE")
    val LONG_VALUE = longPreferencesKey("LONG_VALUE")
    val INT_VALUE = intPreferencesKey("INT_VALUE")

}