package com.example.repositories;

import com.example.domains.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Dept Repository
 * Created by bequs-xhjlee on 2016-10-08.
 */
public interface DeptRepository extends JpaRepository<Dept, Long> {
}
