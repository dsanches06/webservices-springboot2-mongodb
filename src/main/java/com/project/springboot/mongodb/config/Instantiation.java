package com.project.springboot.mongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.project.springboot.mongodb.domain.Post;
import com.project.springboot.mongodb.domain.User;
import com.project.springboot.mongodb.dto.AuthorDTO;
import com.project.springboot.mongodb.repository.PostRepository;
import com.project.springboot.mongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		// limpar a coleção no mongodb
		userRepository.deleteAll();
		postRepository.deleteAll();

		// criar novos objectos
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		// inserir no mongodb
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		// criar post
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		// inserir no mongodb
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		//associar post ao user
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		//salvar outra vez no mongodb
		userRepository.save(maria);
	}
}
