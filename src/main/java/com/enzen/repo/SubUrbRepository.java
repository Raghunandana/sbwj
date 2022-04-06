package com.enzen.repo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Type;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.model.SubUrb;

/* Author: Raghunandana M K
 * Created Date: 01/04/2022
 * Remarks: SubUrbRepository class is the repository for Suburb
 * Modified Date:
 */
public interface SubUrbRepository extends CrudRepository<SubUrb, Long> {

	SubUrb findById(Integer id);
	
	SubUrb findByPostcode(String postcode);
	
	@Query("SELECT suburb, postcode FROM SubUrb s")
	public List<Object> getSuburbList();
	
	List<SubUrb> findByPostcodeContainingOrderBySuburbAsc(String Postcode);
	
	@Query("SELECT suburb, postcode FROM SubUrb s where s.postcode between ?1 and ?2")
	public List<SubUrb> getSuburbRange(String start, String end);
	
	public List<SubUrb> findByPostcodeBetweenOrderBySuburbAsc(String start, String end);
	
	String postListSql = "select suburb, postcode from suburbdata "
			+"where postcode like %?1% ";
	@Query(value = postListSql, nativeQuery = true)
	public List<Object> getRoadsList(Integer ids);
	
	
}
