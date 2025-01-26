package com.shubh.housing_property.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.housing_property.entities.User;

public interface UserRepo extends JpaRepository<User,Long>{
    
}
