package com.yash.SpringSecurityAuthApp.repository;

import com.yash.SpringSecurityAuthApp.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> findByUsername(String username);
}
