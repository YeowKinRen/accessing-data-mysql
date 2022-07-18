package com.example.myapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//import com.example.myapplication.User.UserDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	//////////////////////////////////////////////////////////////////////////////
	// *
	// * Get Mapping
	// *
	//////////////////////////////////////////////////////////////////////////////

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUserById(long id) {
		return userRepository.findById(id);
	}

	public List<User> pageUser(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<User> pagedResult = userRepository.findAll(paging);
		return pagedResult.toList();
	}

	public List<User> pageUser(Pageable pageable) {
		Page<User> pagedResult = userRepository.findAll(pageable);
		return pagedResult.toList();
	}

	public List<User> pageAndFilterUser(int pageNo, int pageSize, String filterEmail, String filterName, String sortBy,
			boolean orderByAsc) {

		Pageable paging = PageRequest.of(pageNo, pageSize,
				((orderByAsc == true) ? Sort.Direction.ASC : Sort.Direction.DESC), sortBy);
		// or sort more than one field
		// Sort sort = Sort.by("name").ascending.and(Sort.by(email).descending())

		List<User> pagedResult = userRepository.findByNameStartingWithAndEmailStartingWith(filterName, filterEmail,
				paging);
		return pagedResult;
	}

	public List<User> searchFilter(Map<String, String> input) {
		return userRepository.searchFilter(input);
	}

	public List<UserProjection> getAllProjectionUsers() {
		return userRepository.findAllBy();
	}

	//////////////////////////////////////////////////////////////////////////////
	// *
	// * Post Mapping
	// *
	//////////////////////////////////////////////////////////////////////////////

	public User addUser(String name, String email) {
		User n = new User();
		n.setName(name);
		n.setEmail(email);
		return userRepository.save(n);
	}

	public void addAll(List<User> users) {
		userRepository.saveAll(users);
	}

	//////////////////////////////////////////////////////////////////////////////
	// *
	// * Delete Mapping
	// *
	//////////////////////////////////////////////////////////////////////////////

	public long deleteUser(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return id;
		} else {
			return -1;
		}
	}

	public void deleteAll(List<Long> ids) {
		userRepository.deleteAllById(ids);
	}

	public void deleteInBatch(List<Long> ids) {
		userRepository.deleteAllByIdInBatch(ids);
	}

	public List<User> deleteUsers(String name, String email) {
		if (name == null) {
			return userRepository.deleteByEmail(email);
		} else if (email == null) {
			return userRepository.deleteByName(name);
		} else {
			return userRepository.deleteByNameAndEmail(name, email);
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	// *
	// * Put Mapping
	// *
	//////////////////////////////////////////////////////////////////////////////

	@Transactional
	public User updateUser(Long userId, String name, String email) {
		Optional<User> userP = userRepository.findById(userId);
		if (userP.isEmpty()) {
			return null;
		} else {
			User user = userP.get();
			if (name != null)
				user.setName(name);
			if (email != null)
				user.setEmail(email);
			return user;
		}

	}

	@Transactional
	public List<User> updateEmail(String targetEmail, String newEmail) {
		List<User> users = userRepository.findByEmail(targetEmail);
		for (User user : users) {
			user.setEmail(newEmail);
		}
		return users;
	}

	@Transactional
	public List<User> updateName(String targetName, String newName) {
		List<User> users = userRepository.findByName(targetName);
		for (User user : users) {
			user.setName(newName);
		}
		return users;
	}

	@Transactional
	public int updateBulk(Map<String, Object> input) {
		return userRepository.updateBulk(input);
	}

}