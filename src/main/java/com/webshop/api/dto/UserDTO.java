package com.webshop.api.dto;

import com.webshop.persistance.entity.Role;
import com.webshop.persistance.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private String created;

    private Set<Role> roles;

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.created = user.getCreated().toString();
        this.roles = user.getRoles();
    }
}
