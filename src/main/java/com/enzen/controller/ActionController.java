package com.enzen.controller;

import java.rmi.ServerException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.enzen.model.SubUrb;
import com.enzen.repo.SubUrbRepository;

/* Author: Raghunandana M K
 * Created Date: 01/04/2022
 * Remarks: ActionController class is used to create service using Rest API
 * Modified Date:
 */

/*Rest Controller used for post code service */
@RestController
@RequestMapping(value = "/action/*")
public class ActionController {
	
	@Autowired
	SubUrbRepository sr;
	
	//Service list the Suburban based on the Postcode value
	@GetMapping(value = "/getSuburbList/{postcode}")
	public List<SubUrb> getPostCodeList(@PathVariable("postcode") String postcode) {
		
		return sr.findByPostcodeContainingOrderBySuburbAsc(postcode.toUpperCase());
	}
	
	//Service used to list all Suburban's
	@GetMapping(value = "/getAllSuburb")
	public List<SubUrb> getAllSuburbList() {
		
		return (List<SubUrb>) sr.findAll();
	}
	
	//Service used list all Suburban based on the selection on Postcode range
	@GetMapping(value = "/getPostcodeRange/{data}")
	public List<SubUrb> getAllSuburbList(@PathVariable("data") String data) {
		String[] arr = data.split("-");
		return sr.findByPostcodeBetweenOrderBySuburbAsc(arr[0].toUpperCase(), arr[1].toUpperCase());
	}
	
	//Service is to save the SubUrb details
	@PostMapping(value = "/SubUrb", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubUrb> createSubUrb(@RequestBody SubUrb subUrb) throws ServerException {
		SubUrb s = subUrb;
		s.setPostcode(s.getPostcode().toUpperCase());
		s = sr.save(s);
		if (s == null) {
	        throw new ServerException("");
	    } else {
	        return new ResponseEntity<>(s, HttpStatus.CREATED);
	    }
	}
	
	//Service is used to save List of Suburb enterd by the user
	@SuppressWarnings("unchecked")
	@PostMapping("/insertSubUrb")
	public JSONObject insertSubUrb(@RequestBody String stringToParse) throws java.text.ParseException{
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		
		try {
			if(stringToParse != null) {
				json.put("issuccess", true);
				json.put("code", 200);
				data = (JSONObject)parser.parse(stringToParse);
				//Integer id = tmr.getMaxTariffId();
				JSONArray list = (JSONArray) data.get("data");
				if(list!=null && list.size()>0) {
					for(int i=0; i<list.size(); i++) {
						JSONObject t = (JSONObject) list.get(i);
						SubUrb tm = new SubUrb();
						tm.setSuburb(t.get("suburb").toString());
						tm.setPostcode(t.get("postcode").toString());
						//tm.setTariffId(tariffId);
						tm = sr.save(tm);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("issuccess", false);
			json.put("code", 500);
		}
		
		return json;
	}
	
	//Service is used to get the information from the user
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value="/getSuburb/{data}", produces="application/json")  
	public @ResponseBody JSONObject getSuburbDetails(HttpServletRequest request, @PathVariable String data){

		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("result", data);
		return obj1;
	}

}
