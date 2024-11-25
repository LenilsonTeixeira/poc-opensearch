package br.com.lteixeira.config

import org.apache.http.HttpHost
import org.opensearch.client.RestClient
import org.opensearch.client.RestHighLevelClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RestHighLevelClientConfiguration {

    @Value("\${opensearch.host}")
    lateinit var host: String

    @Value("\${opensearch.port}")
    var port = 9200

    @Value("\${opensearch.scheme}")
    lateinit var scheme: String

    @Bean
    fun restHighLevelClient(): RestHighLevelClient {
        val builder = RestClient.builder(HttpHost(host, port, scheme))
        return RestHighLevelClient(builder)
    }
}