package com.example.springsecuritykotlin.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.transaction.Transactional

import org.springframework.transaction.annotation.Propagation

import kotlin.reflect.KClass

@Entity
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column(unique = true)
    var name: String? = null,
    var email: String? = null,
     var password: String? = null,
//    @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
//    @ManyToMany
    @JoinTable(
        name = "UserRoles", joinColumns = [JoinColumn(name = "UserId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "RoleId", referencedColumnName = "id")]
    )

    var roles: MutableSet<Role> = mutableSetOf<Role>()
)

//
//    constructor() {}
//    constructor(name: String?, email: String?, password: String?) : super() {
//        this.name = name
//        this.email = email
//        this.password = password
//    }
//
//    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
//        return this.roles
//    }
//
//
//    override fun getPassword(): String {
//        return this.password.toString()
//    }
//
//    override fun getUsername(): String {
//        return this.username
//    }
//
//    override fun isAccountNonExpired(): Boolean {
//        return true
//    }
//
//    override fun isAccountNonLocked(): Boolean {
//        return true
//    }
//
//    override fun isCredentialsNonExpired(): Boolean {
//        return true
//    }
//
//    override fun isEnabled(): Boolean {
//        return true
//    }
//}