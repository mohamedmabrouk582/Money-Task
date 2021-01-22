package com.example.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Budget(
    val id:String,
    val name:String,
    val first_month:String?=null,
    val last_month:String?=null,
    val last_modified_on:String?=null,
    val currency_format:Currency?=null,
    val accounts:ArrayList<Account>?=null
) : Parcelable