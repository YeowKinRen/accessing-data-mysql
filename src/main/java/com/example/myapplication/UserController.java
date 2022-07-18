package com.example.myapplication;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapplication.exception.RecordNotFoundException;

@RestController // This means that this class is a Controller
@RequestMapping(path = "demo") // This means URL's start with /demo (after Application path)
public class UserController {

	@Autowired // @Autowired means to inject collaborating userSepository bean into our
				// UserController bean
	private UserService userService;

	//////////////////////////////////////////////////////////////////////////////
	// *
	// * Get Mapping
	// *
	//////////////////////////////////////////////////////////////////////////////

	@GetMapping("users")
	public List<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userService.getAllUsers();
	}

	@GetMapping("users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id) throws RecordNotFoundException {
		Optional<User> user = userService.getUserById(id);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			throw new RecordNotFoundException();
		}
	}

	@GetMapping(path = "users/{pageNo}/{pageSize}")
	public List<User> pageUser(@PathVariable int pageNo, @PathVariable int pageSize) {
		return userService.pageUser(pageNo, pageSize);
	}

	@GetMapping(path = "users")
	public List<User> pageUser(Pageable pageable) {
		return userService.pageUser(pageable);
	}

	@GetMapping(path = "users")
	public List<User> pageAndFilterUser(@RequestParam int pageNo, @RequestParam int pageSize,
			@RequestParam(required = false, defaultValue = "") String filterEmail,
			@RequestParam(required = false, defaultValue = "") String filterName,
			@RequestParam(required = false, defaultValue = "id") String sortBy,
			@RequestParam(required = false, defaultValue = "true") boolean orderByAsc) {
		return userService.pageAndFilterUser(pageNo, pageSize, filterEmail, filterName, sortBy, orderByAsc);
	}

	@GetMapping(path = "users")
	public List<User> searchFilter(@RequestBody Map<String, String> input) {
		return userService.searchFilter(input);
	}

	@GetMapping("users/projection")
	public List<UserProjection> getAllProjectionUsers() {
		return userService.getAllProjectionUsers();
	}

	//////////////////////////////////////////////////////////////////////////////
	// *
	// * Post Mapping
	// *
	//////////////////////////////////////////////////////////////////////////////

	@PostMapping() // Map ONLY POST Requests
	public ResponseEntity<Long> addUser(@RequestParam String name, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the request
		User user = userService.addUser(name, email);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/addBulk")
	public String addBulk(@RequestBody List<User> users) {
		if (users != null && !users.isEmpty()) {
			userService.addAll(users);
			return String.format("ADDED %d USERS.", users.size());
		} else {
			return "REQUEST_NO_BODY";
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	// *
	// * Delete Mapping
	// *
	//////////////////////////////////////////////////////////////////////////////

	@DeleteMapping(path = "users/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id) {
		long response = userService.deleteUser(id);
		if (response != -1) {
			return new ResponseEntity<>("USER_ID: " + id + " DELETED", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("users")
	public String deleteAll(@RequestBody List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			userService.deleteAll(ids);
			return String.format("DELETED %d USERS.", ids.size());
		} else {
			return "REQUEST_NO_BODY";
		}
	}

	/**
	 * The delete method is going to delete your entity in one operation. The
	 * deleteInBatch is going to batch several delete-statements and delete them as
	 * 1 operation by bypassing the JPA cache.
	 */
	@DeleteMapping("users/deleteInBatch")
	public String deleteInBatch(@RequestBody List<Long> ids) {
		if (ids != null && !ids.isEmpty()) {
			userService.deleteInBatch(ids);
			return String.format("DELETED %d USERS.", ids.size());
		} else {
			return "REQUEST_NO_BODY";
		}
	}

	@DeleteMapping(path = "users")
	public List<User> deleteUsers(@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		return userService.deleteUsers(name, email);
	}

	//////////////////////////////////////////////////////////////////////////////
	// *
	// * Put Mapping
	// *
	//////////////////////////////////////////////////////////////////////////////

	@PutMapping(path = "users/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable("userId") Long userId,
			@RequestParam(required = false) String name, @RequestParam(required = false) String email) {
		User user = userService.updateUser(userId, name, email);
		if (user==null) {
			return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("USER_ID: " + userId + " UPDATED", HttpStatus.OK);
		}
	}

	@PutMapping(path = "user/updateEmail")
	public List<User> updateEmail(@RequestParam String targetEmail, @RequestParam String newEmail) {
		return userService.updateEmail(targetEmail, newEmail);
	}

	@PutMapping(path = "/updateName")
	public List<User> updateName(@RequestParam String targetName, @RequestParam String newName) {
		return userService.updateName(targetName, newName);
	}

	@PutMapping(path = "/updateBulk")
	public String updateBulk(@RequestBody Map<String, Object> input) {
		System.out.println("input data:" + input);
		List<Integer> ids = (List<Integer>) input.get("ids");
		if (ids != null && !ids.isEmpty()) {
			int updateResult = userService.updateBulk(input);
			return String.format("UPDATED %d USERS.", updateResult);
		} else {
			return "ID FIELD EMPTY";
		}
	}

}