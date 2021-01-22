package com.example.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BudgetPayees(
     val data:HashMap<Budget,ArrayList<Payee>>
) : Parcelable