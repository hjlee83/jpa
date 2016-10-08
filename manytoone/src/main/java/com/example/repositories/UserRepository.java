package com.example.repositories;

import com.example.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Repository
 * Created by bequs-xhjlee on 2016-10-08.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
