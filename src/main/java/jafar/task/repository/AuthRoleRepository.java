package jafar.task.repository;

import jafar.task.entity.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {
    Optional<AuthRole> findAuthRoleByCode(String code);
    AuthRole findByCode(String code);
}
