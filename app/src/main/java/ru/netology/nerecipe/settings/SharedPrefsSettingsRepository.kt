package ru.netology.nerecipe.settings

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPrefsSettingsRepository(
    application: Application
) : SettingsRepository {

    private val prefs = application.getSharedPreferences("repo", Context.MODE_PRIVATE)

    override fun saveStateSwitch(key: String, b: Boolean) = prefs.edit().putBoolean(key, b).apply()

    override fun getStateSwitch(key: String): Boolean = prefs.getBoolean(key, true)

    override fun clearStateSwitch(): SharedPreferences.Editor = prefs.edit().clear().apply {  }
}