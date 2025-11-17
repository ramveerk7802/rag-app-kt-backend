package com.example.rag_app_kt.controllers

import com.example.rag_app_kt.services.ChatService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class ChatController(private val chatService: ChatService) {


    @GetMapping("/chat")
     fun chatTemplate(@RequestParam("q") query: String): ResponseEntity<Any>{
        val response = chatService.chatTemplate(query = query)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/save")
    fun saveDataInMariaVectorStore(): ResponseEntity<Any>{
        chatService.saveData()
        return ResponseEntity.ok("Data saved successfully")
    }
}