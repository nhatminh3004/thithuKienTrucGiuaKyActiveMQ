package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void addRole(Role role) {
		roleRepository.save(role);
		
	}

	@Override
	public Role findRoleByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return roleRepository.findRoleByRoleName(roleName);
	}
}
