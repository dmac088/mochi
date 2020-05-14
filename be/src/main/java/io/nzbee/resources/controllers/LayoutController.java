package io.nzbee.resources.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nzbee.Globals;
import io.nzbee.resources.discovery.DiscoveryResource;

@RestController
@RequestMapping("/api")
public class LayoutController {

	@Autowired
	private Globals globalVars;
	
	@GetMapping("/Layout")
	public ResponseEntity<DiscoveryResource> getCategory(@PathVariable String locale, @PathVariable String currency) {
		DiscoveryResource dr = new DiscoveryResource(globalVars.getBaseURL(), locale, currency);
		return ResponseEntity.ok(dr);
	}
	
}
