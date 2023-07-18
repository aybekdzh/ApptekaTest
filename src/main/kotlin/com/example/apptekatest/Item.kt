package com.example.apptekatest

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document;

@Document("items")
data class Item(
        @Id
        val id: String,
        val name: String,
        val description: String,
        val icon: String,
        val price: Double,
        )