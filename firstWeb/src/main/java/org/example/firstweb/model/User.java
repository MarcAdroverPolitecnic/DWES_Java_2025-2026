package org.example.firstweb.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @NonNull
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}