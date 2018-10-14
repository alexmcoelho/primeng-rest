package com.primeng;

import java.util.Arrays;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.primeng.models.Candidate;
import com.primeng.models.Favourite;
import com.primeng.repository.CandidateRepository;
import com.primeng.repository.FavouriteRepository;

@SpringBootApplication
public class PrimengApplication implements CommandLineRunner{
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private FavouriteRepository favouriteRepository;

	public static void main(String[] args) {
		SpringApplication.run(PrimengApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		/*Candidate c1 = new Candidate(14, "cursomc 2", new Date(), "I");
		
		Favourite f1 = new Favourite("12", "Teste 12", c1);
		Favourite f2 = new Favourite("13", "Teste 13", c1);
		Favourite f3 = new Favourite("14", "Teste 14", c1);
		
		c1.getFavouriteCollection().addAll(Arrays.asList(f1, f2));
		
		favouriteRepository.save(Arrays.asList(f1, f2));
		candidateRepository.save(Arrays.asList(c1));*/
		
	}
}
