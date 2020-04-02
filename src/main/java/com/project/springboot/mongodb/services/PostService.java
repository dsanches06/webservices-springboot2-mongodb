package com.project.springboot.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springboot.mongodb.domain.Post;
import com.project.springboot.mongodb.repository.PostRepository;
import com.project.springboot.mongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public List<Post> findAll() {
		return repository.findAll();
	}

	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

	public Post insert(Post obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Post update(Post obj) {
		Post newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Post newObj, Post obj) {
		newObj.setTitle(obj.getTitle());
		newObj.setDate(obj.getDate());	
		newObj.setTitle(obj.getTitle());	
		newObj.setBody(obj.getBody());	
		newObj.setAuthor(obj.getAuthor());	
	}

	public Post fromDTO(Post post) {
		return new Post(post.getId(), post.getDate(), post.getTitle(), post.getBody(), post.getAuthor());
	}
}
