package com.s3corp.service;

import java.util.List;

import com.s3corp.dto.UserDTO;

public interface UserService {

	List<UserDTO> findAll();

	UserDTO findOne(Integer id);

	UserDTO create(UserDTO userDTO);

	UserDTO update(UserDTO userDTO);

	void delete(Integer id);

	UserDTO findByUserNameAndActiveTrue(String userName);

	UserDTO findByUserName(String userName);

}
