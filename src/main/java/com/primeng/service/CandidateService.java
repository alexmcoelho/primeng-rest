package com.primeng.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeng.dto.CandidateDTO;
import com.primeng.dto.CandidateNewDTO;
import com.primeng.models.Candidate;
import com.primeng.models.Favourite;
import com.primeng.repository.CandidateRepository;
import com.primeng.repository.FavouriteRepository;
import com.primeng.service.exception.DataIntegrityException;
import com.primeng.service.exception.ObjectNotFoundException;

@Service
public class CandidateService {
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private FavouriteRepository favouriteRepository;
	
	public Candidate buscar(Integer id) {
		Candidate obj = candidateRepository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! " + id 
					+ " Tipo: " + Candidate.class.getName());
		}
		return obj;
	}
	
	@Transactional
	public Candidate insert(Candidate obj) {
		obj.setId(null);
		obj = candidateRepository.save(obj);
		favouriteRepository.save(obj.getFavouriteCollection());
		return obj;
	}
	
	@Transactional
	public Candidate insert2(Candidate obj) {
		obj.setId(null);
		obj = candidateRepository.save(obj);
		List<Favourite> newListFavourite = new ArrayList<>();
		for (Favourite fav : obj.getFavouriteCollection()) {
			fav.setCandidate(obj);
			newListFavourite.add(fav);
		}
		favouriteRepository.save(newListFavourite);
		return obj;
	}
	
	public Candidate update(Candidate obj) {
		return candidateRepository.save(obj);
	}
	
	public Candidate update2(Candidate obj) {
		List<Favourite> newListFavourite = new ArrayList<>();
		for (Favourite fav : obj.getFavouriteCollection()) {
			fav.setCandidate(obj);
			newListFavourite.add(fav);			
		}
		List<Favourite> listFavouriteDB = new ArrayList<>();
		listFavouriteDB = favouriteRepository.findAllByKeyFavourite(obj.getId());
		for (Favourite favourite : listFavouriteDB) {
			favouriteRepository.delete(favourite);
		}
		
		favouriteRepository.save(newListFavourite);
		return candidateRepository.save(obj);
	}
	
	public void delete(Integer id) {
		try {
			candidateRepository.delete(id);
		}catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir, pois este registro está sendo utilizado por outra entidade!");
		}
	}
	/**
	 * 
	 * @param page -> por qual página irá iniciar
	 * @param linesPerPage -> linhas por página
	 * @param orderBy -> por qual campo será ordenado
	 * @param direction -> qual a direção ASC ou DESC
	 * @return
	 */
	public Page<Candidate> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return candidateRepository.findAll(pageRequest);
	}
	
	public Candidate fromDTO(CandidateDTO objDTO) {
		return new Candidate(objDTO.getId(), objDTO.getName(), objDTO.getDob(), objDTO.getGender());
	}
	
	public Candidate fromDTO(CandidateNewDTO objDTO) {
		Candidate can = new Candidate(null, objDTO.getName(), objDTO.getDob(), objDTO.getGender());
		Favourite fav = new Favourite(objDTO.getFavkey(), objDTO.getFavValue(), can);
		can.getFavouriteCollection().add(fav);
		return can;
	}
}
