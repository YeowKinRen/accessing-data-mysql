package com.example.myapplication;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UserRepositoryImpl implements UserRepositoryCustom {
	
	
	

    @PersistenceContext
    EntityManager entityManager;

	@Override
	public List<User> searchFilter(Map<String, String> input) {
    	// Java Persistence Query Language(JPQL) defined in JPA to interact with Entity
    	StringBuffer jpql = new StringBuffer("SELECT u FROM User u WHERE 1=1");
    	for (Object key : input.keySet()) {
    		jpql.append(" AND u."+key+" LIKE '"+input.get(key)+"%'");
    	}
    	TypedQuery<User> query = entityManager.createQuery(jpql.toString(), User.class);
    	List<User> queryResult = query.getResultList();
    	return queryResult;	
	}
	
	public int updateBulk(Map<String, Object> input) {
		StringBuffer jpql = new StringBuffer("UPDATE User u SET ");
		for (Object key : input.keySet()) {
			if (key != "ids") {
				jpql.append(" u."+key+" = '"+input.get(key)+"'");
			}
		}
		jpql.append(" WHERE u.id in (:ids)");
		Query query = entityManager.createQuery(jpql.toString());
		query.setParameter("ids", input.get("ids"));
		int queryResult = query.executeUpdate();
		return queryResult;
	}
	
	

}
