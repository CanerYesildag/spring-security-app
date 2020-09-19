package com.security.example.demo.domain.repository;

import com.security.example.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u left join fetch u.authorities where u.userName= :userName")
    User findByUserName(@Param("userName") String userName);
}
