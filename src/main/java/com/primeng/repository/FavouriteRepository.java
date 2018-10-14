package com.primeng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.primeng.models.Favourite;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, String>{
	
	@Query("SELECT f FROM Favourite f WHERE f.candidate.id = ?1")
    public List<Favourite> findAllByKeyFavourite(Integer idCandidate);
	
}