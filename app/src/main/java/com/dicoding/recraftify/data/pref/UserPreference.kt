package com.dicoding.recraftify.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
class UserPreference private constructor(private val dataStore: DataStore<Preferences>){

}