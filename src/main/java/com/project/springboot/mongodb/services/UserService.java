package com.project.springboot.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springboot.mongodb.domain.User;
import com.project.springboot.mongodb.dto.UserDTO;
import com.project.springboot.mongodb.repository.UserRepository;
import com.project.springboot.mongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

	public User insert(User obj) {
		return repository.insert(obj);
	}

	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
}
