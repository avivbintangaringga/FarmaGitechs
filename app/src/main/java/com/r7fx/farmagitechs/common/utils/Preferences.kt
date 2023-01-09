package com.r7fx.farmagitechs.common.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences (private val context: Context) {
    private val name = context.packageName + ".SHAREDPREFERENCE"
    private val prefs = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    var token: String
        get() = prefs.getString("token", "")!!
        set(value) = prefs.edit().putString("token", value).apply()
}