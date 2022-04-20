package com.example.myapplication;

import java.util.List;
import java.util.Map;


public interface UserRepositoryCustom {
	
	List<User> searchFilter(Map<String, String> input);
	
	int updateBulk(Map<String, Object> input);


}