package com.exam.repo;

import com.exam.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Role,Long> {
}
