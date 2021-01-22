package com.example.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Currency(
    val iso_code:String,
    val currency_symbol:String
):Parcelable