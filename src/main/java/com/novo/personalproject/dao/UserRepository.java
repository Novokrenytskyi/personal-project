package com.novo.personalproject.dao;

import com.novo.personalproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
