package com.webshop.persistance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webshop.api.dto.UserDTO;
import com.webshop.api.dto.request.RegisterRequest;
import com.webshop.persistance.repository.RoleRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "\"user\"")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    public User (UserDTO userDTO){
        this.id = userDTO.getId();
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.created = LocalDateTime.parse(userDTO.getCreated());
        this.roles = userDTO.getRoles();
    }

    public User(RegisterRequest registerRequest){
        this.username = registerRequest.getUsername();
        this.password = registerRequest.getPassword();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

}
