package com.example.myapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//import com.example.myapplication.User.UserDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String addUser(String name, String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    public ResponseEntity<Long> deleteUser(Long userId) {
    	if (userRepository.existsById(userId)) {
	        userRepository.deleteById(userId);
	        return new ResponseEntity<>(HttpStatus.OK);
    	} else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
    
    @Transactional
    public ResponseEntity<Object> updateUser(Long userId, String name, String email) {
    	Optional<User> userP = userRepository.findById(userId);
    	if (userP.isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	} else {
    		User user = userP.get();
    		if (name != null) 
    			user.setName(name);
    		if (email != null)
    			user.setEmail(email);
    		return new ResponseEntity<>(HttpStatus.OK);
    	}

    }
    
    public List<User> pageUserPath(int pageNo, int pageSize){
    	Pageable paging = PageRequest.of(pageNo, pageSize);
    	Page<User> pagedResult = userRepository.findAll(paging);
    	return pagedResult.toList();
    }
    
    public List<User> pageUser(Pageable pageable) {
    	Page<User> pagedResult = userRepository.findAll(pageable);
    	return pagedResult.toList();
    }
    

    
    public List<User> pageAndFilterUser(int pageNo, 
    		int pageSize, 
    		String filterEmail, 
    		String filterName, 
    		String sortBy, 
    		boolean orderByAsc) {
    	
    	Pageable paging = PageRequest.of(pageNo, pageSize, 
    			((orderByAsc == true)?Sort.Direction.ASC:Sort.Direction.DESC), 
    			sortBy);
    	// or sort more than one field
    	// Sort sort = Sort.by("name").ascending.and(Sort.by(email).descending())
    	
    	List<User> pagedResult = userRepository.findByNameStartingWithAndEmailStartingWith(filterName, filterEmail, paging); 
    	return pagedResult;
    }
    
    
    
    
    
    
    @Transactional
    public List<User> deleteByBatch(String name, String email) {
		if (name == null) {
			return userRepository.deleteByEmail(email);
		} else if (email == null) {
			return userRepository.deleteByName(name);
		} else {
			return userRepository.deleteByNameAndEmail(name, email);
		} 
    }
    
    
    @Transactional
    public List<User> updateEmailByBatch(String targetEmail, String newEmail) {
    	List<User> users = userRepository.findByEmail(targetEmail);
		for (User user:users) {
			user.setEmail(newEmail);
		}
		return users;
    }
    
    @Transactional
    public List<User> updateNameByBatch(String targetName, String newName) {
    	List<User> users = userRepository.findByName(targetName);
		for (User user:users) {
			user.setName(newName);
		}
		return users;
    }
    
    
    public List<User> searchFilter(Map<String, String> input) {
    	return userRepository.searchFilter(input);
    }
    
    @Transactional
    public int updateBulk(Map<String, Object> input) {
    	return userRepository.updateBulk(input);
    }
    
    public void addBulk(List<User> users) {
        userRepository.saveAll(users);
    }
    
    public void deleteBulk(List<Long> ids) {
        userRepository.deleteAllById(ids);
    }
    
//    public  List<UserDto> selectProjection(int ids) {
//  	  return userRepository.findAllBy();//.selectProjection(ids);
//}

    
    
    
    
    
    
}