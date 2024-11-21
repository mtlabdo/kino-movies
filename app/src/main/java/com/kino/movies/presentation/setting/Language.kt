package com.kino.movies.presentation.setting

import android.app.Activity
import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import com.kino.movies.R
import java.util.Locale


sealed class Language {
    abstract val code: String
    abstract val titleRes: Int

    companion object {
        val allowedLocales = listOf(English, French, Hindi, Arabic, Spanish, Italian)

        fun setLocale(context: Context, localeCode: String) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.getSystemService(LocaleManager::class.java).applicationLocales =
                    LocaleList.forLanguageTags(localeCode)
            } else {
                setLocaleForDevicesLowerThanTiramisu(localeCode, context)
            }
        }

        private fun setLocaleForDevicesLowerThanTiramisu(localeTag: String, context: Context) {
            val locale = Locale(localeTag)
            Locale.setDefault(locale)

            val resources = context.resources
            val configuration = resources.configuration
            configuration.setLocale(locale)

            if (context is Activity) {
                context.apply {
                    resources.updateConfiguration(configuration, resources.displayMetrics)
                    recreate()
                }
            } else {
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }
        }
        internal fun getCurrentLanguage(context: Context): Language {
            return this.allowedLocales.find { it.code == getCurrentLocale(context) } ?: English
        }

        fun getLanguageByCode(code: String): Language? {
            return this.allowedLocales.find { it.code == code }
        }

        private fun getCurrentLocale(context: Context): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.getSystemService(LocaleManager::class.java).applicationLocales.toLanguageTags()
            } else {
                AppCompatDelegate.getApplicationLocales().toLanguageTags()
            }
        }
    }


    object English : Language() {
        override val code: String = "en"
        override val titleRes: Int = R.string.english
    }

    object French : Language() {
        override val code: String = "fr"
        override val titleRes: Int = R.string.french
    }

    object Hindi : Language() {
        override val code: String = "hi"
        override val titleRes: Int = R.string.hindi
    }

    object Arabic : Language() {
        override val code: String = "ar"
        override val titleRes: Int = R.string.arabic
    }

    object Spanish : Language() {
        override val code: String = "es"
        override val titleRes: Int = R.string.spanish
    }

    object Italian : Language() {
        override val code: String = "it"
        override val titleRes: Int = R.string.italian
    }

}