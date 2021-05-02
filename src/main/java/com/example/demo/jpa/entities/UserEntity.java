package com.example.demo.jpa.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "users")
@Data
@Embeddable
public class UserEntity {
    private @Id
    @GeneratedValue
    long id;
    private @NotBlank String username;
    private @NotBlank String password;
    private boolean loggedIn;
    private boolean isOwner = false;

    public UserEntity() {
    }

    public UserEntity(@NotBlank String username,
                      @NotBlank String password,
                      boolean isOwner) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
        this.isOwner = isOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

}
