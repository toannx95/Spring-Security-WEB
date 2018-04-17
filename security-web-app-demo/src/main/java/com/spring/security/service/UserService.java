package com.spring.security.service;

import com.spring.security.dto.UserDTO;

public interface UserService {

	UserDTO create(UserDTO userDTO);

	UserDTO findByUserNameAndActiveTrue(String userName);

	UserDTO findByUserName(String userName);

}
