package com.bersyte.rent_a_car.core.token

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bersyte.rent_a_car.features.auth.data.models.AuthResponse
import com.google.gson.Gson
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
    private val authKey = stringPreferencesKey("auth_response")
    private val gson = Gson()

    suspend fun saveAuthResponse(authResponse: AuthResponse) {
        val json = gson.toJson(authResponse)
        Log.d("TOKEN_MANAGER", "Saving AuthResponse JSON: $json")
        dataStore.edit { it[authKey] = json }
    }

    fun getAuthResponseFlow(): Flow<AuthResponse?> = dataStore.data
        .map { prefs ->
            prefs[authKey]?.let { json ->
                runCatching { gson.fromJson(json, AuthResponse::class.java) }.getOrNull()
            }
        }

    suspend fun getAuthResponse(): AuthResponse? {
        val json = dataStore.data.map { it[authKey] }.first()
        val auth = json?.let { gson.fromJson(it, AuthResponse::class.java) }
        Log.d("TOKEN_MANAGER", "Retrieved AuthResponse: $auth")
        return auth
    }

    suspend fun clearAuthResponse() {
        dataStore.edit { it.remove(authKey) }
        Log.d("TOKEN_MANAGER", "AuthResponse cleared")
    }
}
