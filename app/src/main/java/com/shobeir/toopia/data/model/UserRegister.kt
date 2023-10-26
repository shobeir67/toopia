package com.shobeir.toopia.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserRegister(
    val phone: String,
    val code :String
) : Parcelable
