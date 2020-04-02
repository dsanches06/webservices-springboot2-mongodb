package com.project.springboot.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.springboot.mongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	// query method
	List<Post> findByTitleContainingIgnoreCase(String text);

	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
}
