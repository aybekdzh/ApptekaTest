package com.example.apptekatest

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : MongoRepository<Item, String> {
    fun findOneById(id: String): Item
}
