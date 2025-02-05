package com.shubh.housing_property.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.housing_property.entities.User;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
}
