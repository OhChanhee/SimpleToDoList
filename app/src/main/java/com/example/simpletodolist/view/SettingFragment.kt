package com.example.simpletodolist.view

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.simpletodolist.R

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        setPreferencesFromResource(R.xml.setting_prefernces, rootKey)
    }
}