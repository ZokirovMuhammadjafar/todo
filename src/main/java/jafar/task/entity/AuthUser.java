package jafar.task.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;


//    @OneToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "role_id", nullable = false)
//    private AuthRole role;

    private boolean active = true;

    public AuthUser(String username, String password) {
        this.id=null;
        this.username = username;
        this.password = password;
    }

    private boolean blocked;

    public AuthUser() {


    }
}
