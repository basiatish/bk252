package ru.max.bk252_with_coroutines.Data

import android.content.Context
import android.content.SharedPreferences

class SaveAndLoadJson() {

    fun Save(context: Context, JsonOrPass: String, FileName: String, StringName: String) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(FileName, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(StringName, JsonOrPass).apply()
    }

    fun Load(context: Context, FileName: String, StringName: String): String? {
        val sharedPref: SharedPreferences = context.getSharedPreferences(FileName, Context.MODE_PRIVATE)
        val str = sharedPref.getString(StringName, "")
        return str
    }

    fun Delete(context: Context, FileName: String){
        val sharedPref: SharedPreferences = context.getSharedPreferences(FileName, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear().apply()
    }
}