package com.example.rustore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "onboarding")

class OnboardingStore(private val context: Context) {
    private val KEY_SEEN = booleanPreferencesKey("seen")

    suspend fun isSeen(): Boolean =
        context.dataStore.data.map { it[KEY_SEEN] ?: false }.first()

    suspend fun setSeen(value: Boolean) {
        context.dataStore.edit { it[KEY_SEEN] = value }
    }
}
