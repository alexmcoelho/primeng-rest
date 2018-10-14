package com.primeng.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.primeng.dto.CandidateDTO;
import com.primeng.dto.CandidateNewDTO;
import com.primeng.models.Candidate;
import com.primeng.repository.CandidateRepository;
import com.primeng.service.CandidateService;

@RestController
@RequestMapping("/candidate")
@CrossOrigin(origins = "*")
public class CandidateResource {

	@Autowired
	private CandidateRepository er;
	
	@Autowired
	private CandidateService candidateService;
	
	
	//-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Candidate>> listAllUsers() {
        Iterable<Candidate> iterable = er.findAll();
        List<Candidate> candidates = new ArrayList<>();
        for (Candidate c : iterable) {
        	candidates.add(c);
		}
        
        if(candidates.isEmpty()){
        	return new ResponseEntity<List<Candidate>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Candidate>>(candidates, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    public ResponseEntity<List<CandidateDTO>> listAll() {
        List<Candidate> list = er.findAll();
        List<CandidateDTO> listDTO = list.stream().map(obj -> 
        	new CandidateDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
    	Candidate obj = candidateService.buscar(id);
    	return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Candidate obj){
    	
    	obj = candidateService.insert2(obj);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
    			.path("/{id}").buildAndExpand(obj.getId()).toUri();
    	return ResponseEntity.created(uri).build();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Candidate obj, @PathVariable Integer id){
    	obj.setId(id);
    	obj = candidateService.update2(obj);
    	//return ResponseEntity.noContent().build();
    	return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
    	candidateService.delete(id);
    	return ResponseEntity.noContent().build();
    }
    
    
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CandidateDTO>> listPage(
    		@RequestParam(value="page", defaultValue="0") Integer page, 
    		@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
    		@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
    		@RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Candidate> list = candidateService.findPage(page, linesPerPage, orderBy, direction);
        Page<CandidateDTO> listDTO = list.map(obj -> new CandidateDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }
 
}
