package com.webshop.persistance.repository;

import com.webshop.persistance.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
