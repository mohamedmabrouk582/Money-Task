package com.example.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(
    val id:String?=null,
    val name:String,
    val balance:Long?=null,
    val type:AccountType?=null,
    val transfer_payee_id:String?=null,
    val on_budget:Boolean?=null,
    val closed:Boolean?=null,
    val cleared_balance:Long?=null,
    val uncleared_balance:Long?=null,
    val deleted:Boolean?=null
):Parcelable