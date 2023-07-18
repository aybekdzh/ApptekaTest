package com.example.apptekatest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/items")
class HTTPController(private val itemRepository: ItemRepository) {

    @GetMapping
    fun getAllItems(): ResponseEntity<List<Item>> {
        val tasks = itemRepository.findAll()
        return ResponseEntity.ok(tasks)
    }

    @GetMapping("/{id}")
    fun getOneItem(@PathVariable("id") id: String): ResponseEntity<Item> {
        val task = itemRepository.findOneById(id)
        return ResponseEntity.ok(task)
    }
}