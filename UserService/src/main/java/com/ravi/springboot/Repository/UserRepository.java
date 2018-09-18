package com.ravi.springboot.Repository;

import org.springframework.data.repository.CrudRepository;

import com.ravi.springboot.Model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
