package com.spring.security.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.dto.RoleDTO;
import com.spring.security.entity.Role;
import com.spring.security.repository.RoleRepository;
import com.spring.security.service.RoleService;
import com.spring.security.util.converter.DAOConverter;
import com.spring.security.util.converter.DTOConverter;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleDTO create(RoleDTO roleDTO) {
		return DTOConverter.convertRole(roleRepository.save(DAOConverter.convertRole(roleDTO)));
	}

	@Override
	public RoleDTO findByName(String name) {
		Role role = roleRepository.findByName(name);
		if (Objects.isNull(role)) {
			return null;
		}
		return DTOConverter.convertRole(role);
	}

}
