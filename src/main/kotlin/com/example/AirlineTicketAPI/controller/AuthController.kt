package com.example.AirlineTicketAPI.controller

import com.example.AirlineTicketAPI.dto.user.LoginDTO
import com.example.AirlineTicketAPI.dto.Message
import com.example.AirlineTicketAPI.dto.user.RegisterDTO
import com.example.AirlineTicketAPI.model.User
import com.example.AirlineTicketAPI.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
@RequestMapping("api/user")
class AuthController(
        private val userService: UserService
) {
    @PostMapping("register")
    fun register(@RequestBody body: RegisterDTO): Any { //ResponseEntity<User> {
        val user = User()
        user.name = body.name
        user.email = body.email
        user.password = body.password

        return try {
            ResponseEntity.ok(userService.save(user))
        } catch (e: DataIntegrityViolationException) {
            Message("This mail is already in use.")
        }
    }

    @PostMapping("login")
    fun login(
            @RequestBody body: LoginDTO,
            response: HttpServletResponse
    ): ResponseEntity<Any> {
        val user = userService.findByEmail(body.email)
                ?: return ResponseEntity.badRequest().body(Message("User not found."))

        if(!user.comparePassword(body.password))
            return ResponseEntity.badRequest().body(Message("Invalid password"))

        val issuer = user.id.toString()
        val jwt = Jwts.builder()
                .setIssuer(issuer)
                .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // expires after 1 day
                .signWith(SignatureAlgorithm.HS512, "secret") // secret key = "secret"
                .compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true // not accessible at frontend, made for backend only

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Login successful"))
    }

    @GetMapping("status")
    fun user(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if(jwt == null)
                return ResponseEntity.status(401).body(Message("Unauthenticated"))

            return ResponseEntity.ok(
                    userService.findById(
                            Jwts.parser()
                                    .setSigningKey("secret")
                                    .parseClaimsJws(jwt)
                                    .body
                                    .issuer
                                    .toInt()
                    )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("Unauthenticated"))
        }
    }

    @PostMapping("logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0 // will expire immediately

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Logged out successfully"))
    }
}