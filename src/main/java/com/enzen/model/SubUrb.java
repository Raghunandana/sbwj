package com.enzen.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/* Author: Raghunandana M K
 * Created Date: 01/04/2022
 * Remarks: SubUrb class is the model bean for Suburb
 * Modified Date:
 */

@Entity
@Table(name = "suburbdata")
public class SubUrb implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(generator = "suburb_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "suburb_id_seq", sequenceName = "suburb_id_seq",allocationSize=1)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "suburb", nullable = true)
	private String suburb;
	
	@Column(name = "postcode", nullable = true)
	private String postcode;
	
	public SubUrb() {
		super();
	}

	public SubUrb(Integer id, String suburb, String postcode) {
		super();
		this.id = id;
		this.suburb = suburb;
		this.postcode = postcode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}