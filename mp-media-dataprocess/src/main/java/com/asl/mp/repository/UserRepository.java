package com.asl.mp.repository;

import com.asl.mp.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findUserByDesc(String des);
}
