package com.aiaggregator.app.data.provider

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.providerDataStore by preferencesDataStore(name = "providers")

@Singleton
class ProviderRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val KEY_PROVIDER = stringPreferencesKey("provider_type")
    private val KEY_BASE_URL = stringPreferencesKey("base_url")
    private val KEY_API_KEY = stringPreferencesKey("api_key")

    val config: Flow<ProviderConfig?> = context.providerDataStore.data.map { prefs ->
        val type = prefs[KEY_PROVIDER]?.let { runCatching { ProviderType.valueOf(it) }.getOrNull() }
        val baseUrl = prefs[KEY_BASE_URL]
        val apiKey = prefs[KEY_API_KEY]
        if (type != null && !apiKey.isNullOrBlank()) {
            ProviderConfig(type, baseUrl ?: type.defaultBaseUrl, apiKey)
        } else null
    }

    suspend fun save(config: ProviderConfig) {
        context.providerDataStore.edit { prefs ->
            prefs[KEY_PROVIDER] = config.type.name
            prefs[KEY_BASE_URL] = config.baseUrl
            prefs[KEY_API_KEY] = config.apiKey
        }
    }

    suspend fun clear() {
        context.providerDataStore.edit { it.clear() }
    }
}
