package com.s3corp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.s3corp.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

	Role findByName(String name);

}
