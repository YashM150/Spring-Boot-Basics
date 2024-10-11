package com.matthe.ecom.repository;

import com.matthe.ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.status = :status WHERE u.username = :username")
    void updateStatusByUsername(@Param("status") String status, @Param("username") String username);
}
