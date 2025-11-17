package com.example.rag_app_kt.config

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.prompt.ChatOptions
import org.springframework.ai.ollama.OllamaChatModel
import org.springframework.ai.ollama.OllamaEmbeddingModel
import org.springframework.ai.ollama.api.OllamaApi
import org.springframework.ai.ollama.api.OllamaChatOptions
import org.springframework.ai.ollama.api.OllamaEmbeddingOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OllamaConfig {

//    private val ollamaUrl = "http://localhost:11434"
//    @Bean
//    fun chatModel(): OllamaChatModel{
//        return OllamaChatModel.builder()
//            .ollamaApi(OllamaApi.builder()
//                .baseUrl(ollamaUrl)
//                .build())
//            .defaultOptions(OllamaChatOptions.builder()
//                .model("llama3.2:latest")
//                .temperature(0.7)
//                .build()
//                ).build()
//
//
//    }



//    @Bean
//    fun embeddingModel(): OllamaEmbeddingModel{
//        return OllamaEmbeddingModel.builder()
//            .ollamaApi(OllamaApi.builder()
//                .baseUrl(ollamaUrl)
//                .build())
//            .defaultOptions(OllamaEmbeddingOptions.builder()
//                .model("")
//                .build()
//            )
//            .build()
//
//    }
}