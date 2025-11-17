package com.example.rag_app_kt.services

interface ChatService {

    fun chatTemplate(query: String): String?
    fun saveData()
}