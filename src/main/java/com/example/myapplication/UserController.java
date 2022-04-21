package com.example.myapplication;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.example.myapplication.User.UserDto;

@RestController // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class UserController {
  @Autowired // @Autowired means to inject collaborating  userSepository bean into our UserController bean
  private UserService userService;
  
  @PostMapping(path="/add") // Map ONLY POST Requests
  public ResponseEntity<Long> addUser (@RequestParam String name
      , @RequestParam String email) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request
	  return userService.addUser(name, email); 
  }

  @GetMapping(path="/all")
  public List<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return userService.getAllUsers();
  }
  
  
  
  @DeleteMapping(path="delete/{userId}")
  public ResponseEntity<Long> deleteUser(@PathVariable("userId") Long userId) {
	  return userService.deleteUser(userId);
  }
  
  @PutMapping(path="put/{userId}")
  public ResponseEntity<Object> updateUser(@PathVariable("userId")  Long userId, 
		  @RequestParam(required=false) String name, 
		  @RequestParam(required=false) String email) {
	return userService.updateUser(userId,  name, email);  
  }
  
  @GetMapping(path="/PageUser/{pageNo}/{pageSize}")
  public List<User> pageUserPath(@PathVariable int pageNo, @PathVariable int pageSize){
	  return userService.pageUserPath(pageNo, pageSize);
  }
  
  @GetMapping(path="/PageUser")
  public List<User> pageUser(Pageable pageable) {
      return userService.pageUser(pageable);
  }
  
  @DeleteMapping(path="/deleteByBatch")
  public List<User> deleteUser(@RequestParam(required=false) String name, 
		  @RequestParam(required=false) String email) {
	  return userService.deleteByBatch(name, email);
  }
  
  @GetMapping(path="/pageAndFilter")
  public List<User> pageAndFilterUser(@RequestParam int pageNo, 
		  @RequestParam int pageSize, 
		  @RequestParam(required=false, defaultValue="") String filterEmail, 
		  @RequestParam(required=false, defaultValue="") String filterName,
		  @RequestParam(required=false, defaultValue="id") String sortBy, 
		  @RequestParam(required=false, defaultValue="true") boolean orderByAsc) {
      return userService.pageAndFilterUser(pageNo, pageSize, filterEmail, filterName, sortBy, orderByAsc);
  }
  
  @PutMapping(path="/updateEmailByBatch")
  public List<User> updateEmail(@RequestParam String targetEmail, 
		  @RequestParam String newEmail) {
      return userService.updateEmailByBatch(targetEmail, newEmail);
  }
  
  @PutMapping(path="/updateNameByBatch")
  public List<User> updateName(@RequestParam String targetName, 
		  @RequestParam String newName) {
      return userService.updateNameByBatch(targetName, newName);
  }
  
  @GetMapping(path="/searchFilter")
  public List<User> searchFilter(@RequestBody Map<String, String> input) {
	  return userService.searchFilter(input);
  } 
  
  @PutMapping(path="/updateBulk")
  public String updateBulk(@RequestBody Map<String, Object> input) {
	  System.out.println("input data:"+ input);
	  List<Integer> ids = (List<Integer>) input.get("ids");
	  if(ids != null && !ids.isEmpty()) {
		  int updateResult = userService.updateBulk(input);
		  return String.format("UPDATED %d USERS.", updateResult);
	  } else {
		  return "ID FIELD EMPTY";
	  }
  } 
  
  @PostMapping("/addBulk")
  public String addBulk(@RequestBody List<User> users) {
      if(users != null && !users.isEmpty()) {
    	  userService.addBulk(users);
          return String.format("ADDED %d USERS.", users.size());
      } else {
          return "REQUEST_NO_BODY";
      }
  }
  
  @PostMapping("/deleteBulk")
  public String deleteBulk(@RequestBody List<Long> ids) {
      if(ids != null && !ids.isEmpty()) {
    	  userService.deleteBulk(ids);
          return String.format("DELETED %d USERS.", ids.size());
      } else {
          return "REQUEST_NO_BODY";
      }
  }
  
  @PostMapping("/selectprojection")
  public  List<UserProjection> selectProjection() {
    	  return userService.getAllProjectionUsers();

  }
  
  
}