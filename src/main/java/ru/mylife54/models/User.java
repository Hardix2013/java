package ru.mylife54.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Entity
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Transient
    private String emailConfirm;
    private String password;
    @Transient
    private String passwordConfirm;
    @Column(unique = true)
    private String username;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    @Transient
    private MultipartFile imageProfile;
    private String photo;
    private Long balance;
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Product, Integer> bucket;


    public boolean isAdmin(String role) {
        return authorities.toString().contains(role.toUpperCase());
    }

    public Long getBucketPrice() {
        Long price = 0L;
        if (bucket != null) {
            for (Product product : bucket.keySet()) {
                price += product.getPrice();
            }
        }
        return price;
    }
}
