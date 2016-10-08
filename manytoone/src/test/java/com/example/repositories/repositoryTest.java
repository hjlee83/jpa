package com.example.repositories;

import com.example.domains.Dept;
import com.example.domains.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Repository Test
 * Created by bequs-xhjlee on 2016-10-08.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class repositoryTest {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void _1_dept_create() {

        long count = deptRepository.count();

        deptRepository.save(new Dept("부서1"));

        long createCount = deptRepository.count() - count;

        assertThat(createCount, is(1L));
    }

    @Test
    public void _2_user_create() {

        long count = userRepository.count();

        userRepository.save(new User("사원1"));

        long createCount = userRepository.count() - count;

        assertThat(createCount, is(1L));
    }

    @Test
    public void _3_dept_user_one_way_create() {

        long deptCount = deptRepository.count();
        long userCount = userRepository.count();

        deptRepository.save(new Dept("부서1", new ArrayList<User>() {
            {
                add(new User("사원1"));
                add(new User("사원2"));
            }
        }));

        long createDeptCount = deptRepository.count() - deptCount;
        long createUserCount = userRepository.count() - userCount;

        assertThat(createDeptCount, is(1L));
        assertThat(createUserCount, is(2L));

        Dept resultDept = deptRepository.findOne(1L);

        resultDept.getUserCollection().forEach(item ->
                log.info("item {}", item)
        );
    }

    @Test
    public void _4_dept_user_two_way_create() {

        long deptCount = deptRepository.count();
        long userCount = userRepository.count();

        Dept dept = new Dept("부서");
        dept.setUserCollection(new ArrayList<User>() {
            {
                add(new User("사원1", dept));
                add(new User("사원2", dept));
            }
        });
        deptRepository.save(dept);

        long createDeptCount = deptRepository.count() - deptCount;
        long createUserCount = userRepository.count() - userCount;

        assertThat(createDeptCount, is(1L));
        assertThat(createUserCount, is(2L));

        Dept resultDept = deptRepository.findOne(1L);

        resultDept.getUserCollection().forEach(item ->
                log.info("item {}", item)
        );
    }
}
