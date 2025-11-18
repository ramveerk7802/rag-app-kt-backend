package com.example.rag_app_kt.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor
import org.springframework.ai.chat.memory.ChatMemory
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository
import org.springframework.ai.chat.memory.MessageWindowChatMemory
import org.springframework.ai.ollama.api.OllamaChatOptions

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AiConfig {


    companion object{
        private val logger = LoggerFactory.getLogger(AiConfig::class.java)
    }

    @Bean
    fun inMemoryChatMemoryRepository() : InMemoryChatMemoryRepository= InMemoryChatMemoryRepository()

    @Bean
    fun chatMemory(inMemoryChatMemoryRepository: InMemoryChatMemoryRepository): ChatMemory{
        return MessageWindowChatMemory.builder()
            .chatMemoryRepository(inMemoryChatMemoryRepository)
            .maxMessages(10)
            .build()
    }

    @Bean
    fun chatClient(builder: ChatClient.Builder,chatMemory: ChatMemory): ChatClient{
        logger.info("ChatMemoryClass name : ${chatMemory.javaClass.name}")
        val messageChatMemoryAdvisor  = MessageChatMemoryAdvisor.builder(chatMemory).build()
        return builder
            .defaultAdvisors(messageChatMemoryAdvisor)
            .defaultOptions(
                OllamaChatOptions.builder()
                    .model("llama3.2:latest")
                    .temperature(0.7)
                    .build()
            )
            .build()
    }


//    @Bean
//    fun chatClient(builder: ChatClient.Builder,chatMemory: ChatMemory): ChatClient{
//        val messageChatMemoryAdvisor  = MessageChatMemoryAdvisor.builder(chatMemory).build()
//        return builder
//            .defaultAdvisors(messageChatMemoryAdvisor)
//            .defaultOptions(
//                OpenAiChatOptions.builder()
//                    .model("gpt-4o-mini-2024-07-18")
//                    .temperature(0.7)
//                    .maxTokens(200)
//                    .build()
//            )
//            .build()
//    }
}