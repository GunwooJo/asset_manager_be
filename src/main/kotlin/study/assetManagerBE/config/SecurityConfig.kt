package study.assetManagerBE.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig {

    @Value("\${cors.allowedOrigin}")
    private lateinit var allowedOrigin: String

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.configurationSource(corsConfigurationSource()) }  // CORS 설정 적용
            .csrf { it.disable() } // 쿠키, 세션 기반 인증에서는 활성화 해야 됨
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/**").permitAll()
                    .anyRequest().authenticated()
            }

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf(allowedOrigin)  // 허용할 Origin
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")  // 허용할 HTTP 메서드(OPTIONS는 spring security가 자동으로 허용
        configuration.allowedHeaders = listOf("Authorization", "Content-Type")  // 허용할 HTTP 헤더
        configuration.allowCredentials = true  // 인증 정보 포함 여부

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)  // 모든 경로에 CORS 설정 적용
        return source
    }
}