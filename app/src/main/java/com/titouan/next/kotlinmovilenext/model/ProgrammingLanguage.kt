package com.titouan.next.kotlinmovilenext.model

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

data class ProgrammingLanguage(
        @DrawableRes
        val imageResourceId: Int,
        val title: String,
        val year: Int,
        @StringRes
        val description: Int)
