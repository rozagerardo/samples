package com.example.demors;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
	
	@GetMapping
	public Map<String,String> retrieveResource(){
		return Map.of("Resource-key-1", "Resource value 1");
	}

}
