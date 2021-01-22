package com.example.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Payee(
    val id:String,
    val name:String,
    val transfer_account_id:String?=null,
    val deleted:Boolean?=null
): Parcelable