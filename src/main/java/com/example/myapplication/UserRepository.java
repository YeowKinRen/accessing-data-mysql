package com.example.myapplication;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
	
    public List<User> findByEmailStartingWith(String email);
    
    public List<User> findByNameStartingWith(String name);
    
    public List<User> findByNameStartingWithAndEmailStartingWith(String name, String email, Pageable paging);
    
    public List<User> deleteByName(String name);
    
    public List<User> deleteByNameAndEmail(String name, String email);
    
    public List<User> deleteByEmail(String email);
    
    public List<UserProjection> findAllBy();
    
    
    
//    // custom query creation using @Query annotation  
//    @Query("select u from User u where u.email = ?1")
//    public List<User> findByEmail(String email); 
//    
    // derived query creation from method names
    public List<User> findByEmail(String email);
    
    public List<User> findByName(String name);
    
    public List<User> findById(int[] id);
    

//    @Modifying
//    @Query("UPDATE User u SET u.email = :email WHERE u.id in (:ids)")
//    public void updateEmailById(@Param("email") String email, @Param("ids") List<Integer> id);
//    
//    
    
    
    

}