package com.emirhanok20.chickenchronicles.data.repository

import com.emirhanok20.chickenchronicles.navigation.game.GameState
import kotlinx.serialization.json.Json
import platform.Foundation.NSUserDefaults

class AppRepositoryImpl : AppRepository {
    private val userDefaults = NSUserDefaults.standardUserDefaults()

    override fun saveState(state: GameState) {
        try {
            val jsonStr = Json.encodeToString(state)
            userDefaults.setObject(jsonStr, forKey = APP_DATA_KEY)
        } catch (_: Exception) {
        }
    }

    override fun getSavedState(): GameState {
        return if (userDefaults.objectForKey(APP_DATA_KEY) != null) {
            try {
                val jsonStr = userDefaults.stringForKey(APP_DATA_KEY) ?: ""
                Json.decodeFromString<GameState>(jsonStr)
            } catch (_: Exception) {
                GameState()
            }
        } else {
            GameState()
        }
    }

    companion object {
        private const val APP_DATA_KEY = "app_data"
    }
}