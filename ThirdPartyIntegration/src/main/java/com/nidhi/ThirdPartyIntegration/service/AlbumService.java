package com.nidhi.ThirdPartyIntegration.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.nidhi.ThirdPartyIntegration.Exceptions.GlobalExceptionHandler;
import com.nidhi.ThirdPartyIntegration.Exceptions.ResourceNotFoundException;

@Service
public class AlbumService {

	@Autowired
	RestTemplate restTemplate;
	
	String url = "https://jsonplaceholder.typicode.com/posts";
	StringBuilder str = new StringBuilder(url);
	
	@Autowired
	GlobalExceptionHandler globalExceptionHandler;

	
	public List<Map<String,Object>> getAlbums() {
		String urlGetAlbums = str.toString();
		ResponseEntity<List> response=null;
		try {
			response = restTemplate.exchange(urlGetAlbums, HttpMethod.GET, null, List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.getBody();
	}


	public Map<String, Object> getAlbumById(int id){
		String urlGetAlbumsById = str.append("/"+id).toString();
		ResponseEntity<Map> albumResponse=new ResponseEntity<Map>(HttpStatus.OK);
		try {
			albumResponse = restTemplate.exchange(urlGetAlbumsById, HttpMethod.GET, null, Map.class);
			if(!albumResponse.getStatusCode().is2xxSuccessful()) {
				throw new ResourceNotFoundException(id);
			}
			return albumResponse.getBody();
		}catch (HttpClientErrorException rnfe) {
			// TODO: handle exception
			return globalExceptionHandler.resourceNotFoundExceptionHandler(rnfe);
		}
		catch (Exception e) {
			return globalExceptionHandler.exceptionHandler(e);
		}
	}
} 
