package study.assetManagerBE.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class AuthService (

) {
    fun googleLogin(code: String) {
        val restClient: RestClient = RestClient.create()
    }
}