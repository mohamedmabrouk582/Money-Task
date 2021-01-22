package com.example.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Transaction(
    val id:String?=null,
    val account_id:String?=null,
    val account_name:String?=null,
    val payee_id:String?=null,
    val payee_name:String?=null,
    val transfer_account_id:String?=null,
    val transfer_transaction_id:String?=null,
    val date:String?=null,
    val amount:Long?=null,
    val memo:String?=null,
    val cleared:String?=null,
    val approved:Boolean?=null,
    val category_id:String?=null,
    val category_name:String?=null,
    val matched_transaction_id:String?=null,
    val import_id:String?=null,
    val type:String?=null,
    val deleted:Boolean?=null
): Parcelable