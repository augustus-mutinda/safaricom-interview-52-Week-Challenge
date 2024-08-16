package com.safaricom.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savings")
data class Savings(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val week: Int,
    val amount: Double,
    var saved: Boolean = false
)