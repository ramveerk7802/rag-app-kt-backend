package com.example.rag_app_kt.services.impl

import com.example.rag_app_kt.helper.Helper
import com.example.rag_app_kt.services.ChatService
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.document.Document
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class ChatServiceImpl(private val chatClient: ChatClient,private val vectorStore: VectorStore) : ChatService {

    @Value("classpath:/prompts/system-message.st")
    private lateinit var systemMessage: Resource

    @Value("classpath:/prompts/user-message.st")
    private lateinit var userMessage: Resource

    override fun chatTemplate(query: String): String? {
//        load similar data from vector store
//        similar result from user query

        return chatClient.prompt()
            .system { s->s.text(this.systemMessage) }
            .user { u->
            u.text(this.userMessage).param("concept",query)
        }.call()
            .content()
    }

    override fun saveData() {
        val list = Helper.getData()
        val documentList = list.stream().map { item-> Document(item) }.toList()
        vectorStore.add(documentList)
    }
}