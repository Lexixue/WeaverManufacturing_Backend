package com.csi.weavermanufacturing_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.csi.weavermanufacturing_backend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
