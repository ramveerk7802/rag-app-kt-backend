package com.example.rag_app_kt

import com.example.rag_app_kt.services.ChatService
import org.junit.jupiter.api.Test
import org.springframework.ai.document.Document
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RagAppKtApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Autowired
	private lateinit var chatService: ChatService
	@Autowired
	private lateinit var vectorStore: VectorStore

	@Test
	fun saveDataInVectorStore(){
		println("Vector store in mariadb start")
		chatService.saveData()

		println("Vector store in mariadb end")
	}

}
