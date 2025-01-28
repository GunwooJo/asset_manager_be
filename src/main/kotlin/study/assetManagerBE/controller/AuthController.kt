package study.assetManagerBE.controller

import org.springframework.web.bind.annotation.*

@RequestMapping("/auth")
@RestController
class AuthController (

) {
    @GetMapping("/google/callback}")
    fun googleGetAuthenticationCode(@RequestParam code: String) {


    }
}