package com.example.domain.models

data class TransactionRequest(
        val transaction:Transaction,
        val transaction_ids:ArrayList<String>?=null
)