package com.partyrole.controllers;

import java.util.List;

import javax.json.JsonMergePatch;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partyrole.entities.PartyRole;
import com.partyrole.exception.PartyRoleNotFoundException;
import com.partyrole.services.PartyRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/partyRole")
@Api(value="PartyRoleController")
public class PartyRoleController {

	@Autowired
	PartyRoleService partyRoleService;

	@GetMapping
	@ApiOperation(value="Fetch all party roles", response = PartyRole.class)
	public ResponseEntity<List<PartyRole>> getAllPartyRoles() {
		return ResponseEntity.ok(partyRoleService.getPartyRoles());
	}

	@GetMapping(value="/{id}")
	@ApiOperation(value="Fetch party role", response = PartyRole.class)
	public ResponseEntity<PartyRole> getPartyRole(@PathVariable String id) throws PartyRoleNotFoundException {
		return new ResponseEntity<>(partyRoleService.getPartyRoleById(id),HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value="Create new party role")
	public ResponseEntity<PartyRole> addPartyRole(@RequestBody @Valid PartyRole partyRole) {
		return new ResponseEntity<>(partyRoleService.add(partyRole),HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value="Update the party role", response = PartyRole.class)
	public ResponseEntity<PartyRole> updatePartyRole(@PathVariable String id, @RequestBody PartyRole partyRole) throws PartyRoleNotFoundException  {
		partyRole.setId(id);
		return new ResponseEntity<>(partyRoleService.updatePartyRole(id, partyRole),HttpStatus.OK);
	}

	@PatchMapping(path = "/{id}", consumes = "application/merge-patch+json")
	@ApiOperation(value = "Patch the party role")
	public void updatePartyRoleFields(@PathVariable String id, @RequestBody JsonMergePatch mergePatchDocument)
			throws PartyRoleNotFoundException {

		partyRoleService.mergePartyRole(id, mergePatchDocument);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value="Delete party role")
	public void deletePartyRole(@PathVariable String id) throws PartyRoleNotFoundException  {
		 partyRoleService.deletePartyRole(id);
	}
}
