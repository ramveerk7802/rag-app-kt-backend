package com.example.rag_app_kt.config

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor
import org.springframework.ai.chat.memory.ChatMemory
import org.springframework.ai.ollama.api.OllamaChatOptions

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AiConfig {

    @Bean
    fun chatClient(builder: ChatClient.Builder,chatMemory: ChatMemory): ChatClient{
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