package com.example.springsecuritykotlin.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "posts")
class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @NotNull

    @Column(unique = true)
    var title: String? = null

    @NotNull

    var description: String? = null

    @NotNull
    var content: String? = null

    @JsonIgnore
    @ManyToOne // один обьект поля ниже, будет содержать множество постов
    @JoinColumn(name = "user_profile_id")
    var userProfile: UserProfile? = null

//    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
//    @JoinTable(
//        name = "post_tags",
//        joinColumns = [JoinColumn(name = "post_id")],
//        inverseJoinColumns = [JoinColumn(name = "tag_id")]
//    )
//    var tags: Set<Tag> = HashSet<Tag>()
    override fun toString(): String {
        return content + " " + title + " " + description
    }
}