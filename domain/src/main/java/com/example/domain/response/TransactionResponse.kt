package com.example.domain.response

import com.example.domain.models.Transaction

data class TransactionResponse(
    val transactions:ArrayList<Transaction>
)