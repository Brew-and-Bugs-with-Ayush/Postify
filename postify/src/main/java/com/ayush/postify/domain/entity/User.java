package com.ayush.postify.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "author" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(createdAt, user.createdAt) && Objects.equals(posts, user.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, createdAt, posts);
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
}
