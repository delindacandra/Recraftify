package com.dicoding.recraftify.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
class UserPreference private constructor(private val dataStore: DataStore<Preferences>){

    suspend fun saveSession(user: UserModel){
        dataStore.edit { pref ->
            pref[EMAIL_KEY] = user.email
            pref[TOKEN_KEY] = user.token
            pref[IS_LOGIN_KEY] = true
        }
    }
    fun getSession(): Flow<UserModel>{
        return dataStore.data.map { pref ->
            UserModel(
                pref[EMAIL_KEY] ?: "",
                pref[TOKEN_KEY] ?: "",
                pref[IS_LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun logout(){
        dataStore.edit { pref ->
            pref.clear()
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: UserPreference? = null
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this){
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }

    }
}