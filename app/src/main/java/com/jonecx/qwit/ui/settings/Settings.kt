package com.jonecx.qwit.ui.settings

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jonecx.qwit.ui.settings.SettingsDataStore.PrefKeys.PREF_IS_NEW_USER
import com.jonecx.qwit.ui.settings.SettingsDataStore.PrefKeys.PREF_OAUTH_TOKEN
import com.jonecx.qwit.ui.settings.SettingsDataStore.PrefKeys.PREF_OAUTH_TOKEN_SECRET
import com.jonecx.qwit.ui.settings.SettingsDataStore.PrefKeys.PREF_SCREEN_NAME
import com.jonecx.qwit.ui.settings.SettingsDataStore.PrefKeys.PREF_USER_ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface Settings {
    suspend fun setNewUser(isNewUser: Boolean)
    val isNewUser: Flow<Boolean>

    suspend fun setUserId(userId: String)
    val userId: Flow<String>

    suspend fun setScreenName(screenName: String)
    val screenName: Flow<String>

    suspend fun saveOauthToken(oauthToken: String)
    val oauthToken: Flow<String>

    suspend fun saveOauthTokenSecret(oauthTokenSecret: String)
    val oauthTokenSecret: Flow<String>
}

class SettingsDataStore(private val context: Context) : Settings {

    companion object {
        private val Context.dataStore by preferencesDataStore(
            name = "qwit_prefs"
        )
    }

    object PrefKeys {
        val PREF_IS_NEW_USER = booleanPreferencesKey("pref_is_new_user")
        val PREF_USER_ID = stringPreferencesKey("pref_user_id")
        val PREF_SCREEN_NAME = stringPreferencesKey("pref_screen_name")
        val PREF_OAUTH_TOKEN = stringPreferencesKey("pref_oauth_token")
        val PREF_OAUTH_TOKEN_SECRET = stringPreferencesKey("pref_oauth_token_secret")
    }

    private val dataStore = context.dataStore

    override suspend fun setNewUser(isNewUser: Boolean) {
        dataStore.edit { it[PREF_IS_NEW_USER] = isNewUser }
    }

    override val isNewUser: Flow<Boolean> =
        dataStore.data.map { it[PREF_IS_NEW_USER] ?: true }

    // TODO: keep this in encrypted shared pref or encrypt them and store them here
    override suspend fun saveOauthToken(oauthToken: String) {
        dataStore.edit { it[PREF_OAUTH_TOKEN] = oauthToken }
    }

    override val oauthToken: Flow<String>
        get() = dataStore.data.map { it[PREF_OAUTH_TOKEN].orEmpty() }

    // TODO: keep this in encrypted shared pref or encrypt them and store them here
    override suspend fun saveOauthTokenSecret(oauthTokenSecret: String) {
        dataStore.edit { it[PREF_OAUTH_TOKEN_SECRET] = oauthTokenSecret }
    }

    override val oauthTokenSecret: Flow<String>
        get() = dataStore.data.map { it[PREF_OAUTH_TOKEN_SECRET].orEmpty() }

    override suspend fun setUserId(userId: String) {
        dataStore.edit { it[PREF_USER_ID] = userId }
    }

    override val userId: Flow<String>
        get() = dataStore.data.map { it[PREF_USER_ID].orEmpty() }

    override suspend fun setScreenName(screenName: String) {
        dataStore.edit { it[PREF_SCREEN_NAME] = screenName }
    }

    override val screenName: Flow<String>
        get() = dataStore.data.map { it[PREF_SCREEN_NAME].orEmpty() }
}
