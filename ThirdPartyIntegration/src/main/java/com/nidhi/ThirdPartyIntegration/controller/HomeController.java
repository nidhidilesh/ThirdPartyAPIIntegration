package com.nidhi.ThirdPartyIntegration.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nidhi.ThirdPartyIntegration.service.AlbumService;

@RestController
public class HomeController {

	AlbumService albumService;
	
	@Autowired
	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}
	@GetMapping("/get-albums")
	public List<Map<String,Object>> showAll() {
		return albumService.getAlbums();
	}
	@GetMapping("/get-albums/{id}")
	public Map<String,Object> getAlbumById(@PathVariable int id){
		return albumService.getAlbumById(id);
	}
}
