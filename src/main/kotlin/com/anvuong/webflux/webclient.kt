package com.anvuong.webflux

import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

class MyWebClient {
    fun getWebContent(url: String): Mono<String> {
        val webClient = WebClient.builder().baseUrl(url).build()
        return webClient.get().retrieve().bodyToMono(String::class.java)
    }
}