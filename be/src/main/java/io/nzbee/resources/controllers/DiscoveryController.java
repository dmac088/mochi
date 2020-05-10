package io.nzbee.resources.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.resources.discovery.DiscoveryResource;

@RestController
@RequestMapping("/api")
public class DiscoveryController {

	@GetMapping("/Discovery/{locale}/{currency}")
	public ResponseEntity<DiscoveryResource> getCategory(@PathVariable String locale, @PathVariable String currency) {
		DiscoveryResource dr = new DiscoveryResource(locale, currency);
		return ResponseEntity.ok(dr);
	}
	
}
