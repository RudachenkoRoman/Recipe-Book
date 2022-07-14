package ru.netology.nerecipe.settings

import android.content.SharedPreferences


interface SettingsRepository {

    fun saveStateSwitch(key: String, b: Boolean)
    fun getStateSwitch(key: String):Boolean
    fun clearStateSwitch(): SharedPreferences.Editor
}