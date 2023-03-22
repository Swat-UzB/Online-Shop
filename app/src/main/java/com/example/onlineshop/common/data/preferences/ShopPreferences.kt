package com.example.onlineshop.common.data.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Zayniddinov Ilyosjon on 21/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
@Singleton
class ShopPreferences @Inject constructor(
    @ApplicationContext context: Context
) : Preferences {
    companion object {
        const val PREFERENCES_NAME = "SHOP_PREFERENCES"
        const val KEY_USER_NAME = "USER_NAME"
    }

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun putUserName(userName: String) {
        edit { putString(KEY_USER_NAME, userName) }
    }

    private inline fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(preferences.edit()) {
            block()
            commit()
        }
    }

    override fun getUserName(): String {
        return preferences.getString(KEY_USER_NAME, "").orEmpty()
    }

    override fun deleteUserName() {
        edit { remove(KEY_USER_NAME) }
    }

}