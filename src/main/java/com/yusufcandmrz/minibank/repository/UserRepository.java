package com.yusufcandmrz.minibank.repository;

import com.yusufcandmrz.minibank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
