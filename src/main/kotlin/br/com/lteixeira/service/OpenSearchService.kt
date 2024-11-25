package br.com.lteixeira.service

import org.opensearch.action.admin.indices.delete.DeleteIndexRequest
import org.opensearch.action.delete.DeleteRequest
import org.opensearch.action.get.GetRequest
import org.opensearch.action.index.IndexRequest
import org.opensearch.client.Request
import org.opensearch.client.RequestOptions
import org.opensearch.client.Response
import org.opensearch.client.RestHighLevelClient
import org.opensearch.client.indices.CreateIndexRequest
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import kotlin.collections.HashMap

@Service
class OpenSearchService(private val client: RestHighLevelClient) {

    @EventListener(ApplicationReadyEvent::class)
    fun onApplicationReady() {
        println(testConnection())
        println(createIndex())
        println(addDocumentFromIndexBike())
        println(getDocumentFromIndexBike())
        println(deleteDocumentFromIndexBike())
        println(deleteIndexBike())
    }

    fun testConnection(): String {
        // Realiza uma requisição GET na raiz (endpoint "/") para testar a conexão
        val request = Request("GET", "/")
        val response: Response = client.lowLevelClient.performRequest(request)
        return "Connection status: ${response.statusLine}"
    }

    fun createIndex(): String {
        val index = "bike"
        val createIndexRequest = CreateIndexRequest(index)
        val createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT)
        return "Index created: ${createIndexResponse.index()} - ${createIndexResponse.isAcknowledged}"
    }

    fun addDocumentFromIndexBike(): String {
        val index = "bike"
        val request = IndexRequest(index)
        request.id("1")
        val stringMapping = HashMap<String, String>()
        stringMapping.put("message:", "Bike Caloi")
        request.source(stringMapping)
        val indexResponse = client.index(request, RequestOptions.DEFAULT)
        return indexResponse.toString()
    }

    fun getDocumentFromIndexBike(): String {
        val index = "bike"
        val id = "1"
        val request = GetRequest(index, id)
        val response = client.get(request, RequestOptions.DEFAULT)
        return response.sourceAsString
    }

    fun deleteDocumentFromIndexBike(): String {
        val index = "bike"
        val id = "1"
        val deleteDocumentRequest = DeleteRequest(index, id)
        val deleteResponse = client.delete(deleteDocumentRequest, RequestOptions.DEFAULT)
        return "document ${deleteResponse.id} deleted"
    }

    fun deleteIndexBike(): String {
        val index = "bike"
        val request = DeleteIndexRequest(index)
        val deleteIndexResponse = client.indices().delete(request, RequestOptions.DEFAULT)
        return " index ${index} deleted: ${deleteIndexResponse.isAcknowledged}"
    }
}