package com.matthe.ecom.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class User {
    @Id
    private long id;
    @Column(unique = true)
    private String username;
    private String password;

//    @Setter
//    @Getter
//    @ElementCollection(fetch = FetchType.EAGER)
//    private Set<String> roles; // A set of roles like ["ROLE_USER", "ROLE_ADMIN"]

}
