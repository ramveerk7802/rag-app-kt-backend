package com.example.rag_app_kt.services.impl

import com.example.rag_app_kt.helper.Helper
import com.example.rag_app_kt.services.ChatService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor
import org.springframework.ai.document.Document
import org.springframework.ai.vectorstore.SearchRequest
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service



@Service
class ChatServiceImpl(private val chatClient: ChatClient,private val vectorStore: VectorStore) : ChatService {


    companion object{
        private val logger: Logger = LoggerFactory.getLogger(ChatServiceImpl::class.java)
    }
    @Value("classpath:/prompts/system-message.st")
    private lateinit var systemMessage: Resource

    @Value("classpath:/prompts/user-message.st")
    private lateinit var userMessage: Resource

    override fun chatTemplate(query: String): String? {
//        load similar data from vector store

//        This is manually similarity search in rag
//        val searchRequest = SearchRequest.builder()
//            .topK(4)
//            .similarityThreshold(0.2)
//            .query(query)
//            .build()
//        val documents = vectorStore.similaritySearch(searchRequest)
//        val list = documents.stream().map { document->document.text }.toList()
//        val context = list.joinToString(", ")
//
//        logger.info("context : $context")
//        similar result from user query

//        pass in context

//        return chatClient.prompt()
//            .system { s->s.text(this.systemMessage).param("documents",context) }
//            .user { u->
//            u.text(this.userMessage).param("query",query)
//        }.call()
//            .content()

//        For Auto similarity search using QuestionAnswerAdvisor
//        for default
        val questionAnswerAdvisor  = QuestionAnswerAdvisor.builder(this.vectorStore).build()
//        for custom
//        val questionAnswerAdvisor = QuestionAnswerAdvisor.builder(this.vectorStore)
//            .searchRequest(SearchRequest.builder()
//                .topK(3)
//                .similarityThreshold(0.2)
//                .build())
//            .build()
        return chatClient.prompt()
            .advisors(questionAnswerAdvisor)
            .user { u->
                u.text(this.userMessage).param("query",query)
            }.call()
            .content()
    }

    override fun saveData() {
        val list = Helper.getData()
        val documentList = list.stream().map { item-> Document(item) }.toList()
        vectorStore.add(documentList)
    }
}