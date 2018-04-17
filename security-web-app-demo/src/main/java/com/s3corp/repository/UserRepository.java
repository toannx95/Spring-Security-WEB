package com.s3corp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.s3corp.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUserNameAndActiveTrue(String userName);

	User findByUserName(String userName);

}