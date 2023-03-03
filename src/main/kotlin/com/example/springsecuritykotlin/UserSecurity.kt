//package com.example.springsecuritykotlin
//
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
//
//class UserSecurity(
//    val id: Int?,
//    val email: String?,
//     val uPassword: String?,
//     val uAuthorities: MutableCollection<GrantedAuthority>?
//) : UserDetails {
//    override fun getAuthorities() = uAuthorities
//    override fun getPassword() = uPassword
//    override fun getUsername() = email
//    override fun isAccountNonExpired() = true
//    override fun isAccountNonLocked() = true
//    override fun isCredentialsNonExpired() = true
//    override fun isEnabled() = true
//}
