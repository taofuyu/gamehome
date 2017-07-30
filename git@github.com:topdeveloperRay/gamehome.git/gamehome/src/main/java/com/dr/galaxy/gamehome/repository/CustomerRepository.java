package com.dr.galaxy.gamehome.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dr.galaxy.gamehome.model.Article;
import com.dr.galaxy.gamehome.model.Customer;
@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, String>{
	
	// public Customer findByWxId(String wxId);
	 
	// public void deleteByWxId(String wxId);//when add @Transactional, this method can work

	@Query(value="select c from Customer c")
	public List<Customer> findRankWithPageable(Pageable rankPageable);

}
