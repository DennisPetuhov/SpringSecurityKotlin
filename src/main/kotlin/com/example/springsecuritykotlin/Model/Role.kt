package com.example.springsecuritykotlin.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority


@Entity(name = "roles")
class Role:GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Enumerated(EnumType.STRING)
    var name: ERole? = null
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private var user: HashSet<User> = HashSet()

//    constructor() {}
//    constructor(name: ERole?) {
//        this.name = name
//    }

    override fun getAuthority(): String {
       return user.toList().toString()
    }
}