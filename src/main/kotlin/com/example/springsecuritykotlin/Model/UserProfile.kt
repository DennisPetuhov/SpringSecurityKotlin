package com.example.springsecuritykotlin.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList


@Entity
@Table(name = "user_profiles")
class UserProfile : Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var name: String? = null
    var email: String? = null

    @Column(name = "phone_number")

    var phoneNumber: String? = null

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private var gender: Gender? = null

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    var dateOfBirth: Date? = null





    var address1: String? = null


    var address2: String? = null


    var street: String? = null


    var city: String? = null


    var state: String? = null


    var country: String? = null

    @Column(name = "zip_code")

    var zipCode: String? = null
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = true) //    @JoinColumn(name = "user_id", nullable = false)
//    @JoinColumn(name = "original_user_id")
    @MapsId
    var user: User? = null


    @OneToMany(mappedBy = "userProfile")
    var post: List<Post> = ArrayList<Post>()

    constructor()
    constructor(
        name:String?, email:String?
    ) {
        this.name=name
        this.email=email
    }
    constructor(
        name:String?, email:String?,
        phoneNumber: String?, gender: Gender?, dateOfBirth: Date?,
        address1: String?, address2: String?, street: String?, city: String?,
        state: String?, country: String?, zipCode: String?
    ) {
        this.name=name
        this.email=email
        this.phoneNumber = phoneNumber
        this.gender = gender
        this.dateOfBirth = dateOfBirth
        this.address1 = address1
        this.address2 = address2
        this.street = street
        this.city = city
        this.state = state
        this.country = country
        this.zipCode = zipCode
    }

    fun getGender(): Gender? {
        return gender
    }

    fun setGender(gender: Gender?) {
        this.gender = gender
    }

    override fun toString(): String {
        return "UserProfile{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", user=" + user +
                '}'
    }
}