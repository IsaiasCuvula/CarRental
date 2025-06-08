package com.bersyte.rent_a_car.core.token

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore
    private val tokenKey = stringPreferencesKey("auth_token")

    suspend fun saveToken(token: String) {
        dataStore.edit { it[tokenKey] = token }
    }

    fun getTokenFlow(): Flow<String?> = dataStore.data
        .map { it[tokenKey] }

    suspend fun getToken(): String? = dataStore.data
        .map { it[tokenKey] }
        .first()

    suspend fun clearToken() {
        dataStore.edit { it.remove(tokenKey) }
    }
}
