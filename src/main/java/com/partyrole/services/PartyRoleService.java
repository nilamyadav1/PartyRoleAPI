package com.partyrole.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.json.JsonMergePatch;
import javax.json.JsonValue;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.partyrole.entities.PartyRole;
import com.partyrole.exception.PartyRoleNotFoundException;
import com.partyrole.notification.Notification;
import com.partyrole.notification.NotificationService;
import com.partyrole.repository.PartyRoleRepository;

@Service
@Transactional
public class PartyRoleService {

	@Autowired
	PartyRoleRepository partyRoleRepo;

	@Autowired
	NotificationService notificationService;

	@Autowired
	ObjectMapper mapper;

	/**
	 * Returns all the party roles
	 * @param id
	 * @param mergePatchDocument
	 */
	public List<PartyRole> getPartyRoles(){
		return partyRoleRepo.findAll();
	}

	/**
	 * Adds the party role
	 * @param id
	 * @param mergePatchDocument
	 */
	public PartyRole add(PartyRole partyRole) {
		partyRole.setIsActive(true);
		PartyRole newPartyRole = partyRoleRepo.save(partyRole);
		
		Notification notification = new Notification();
		notification.setEventTime(LocalDateTime.now());
		notification.setEventType("PartyRoleCreateEvent");
		notification.setEvent(newPartyRole);

		notificationService.triggerEvent(notification);
		return newPartyRole;
	}

	/**
	 * Returns the party role by id
	 * @param id
	 * @param mergePatchDocument
	 * @throws PartyRoleNotFoundException 
	 */
	public PartyRole getPartyRoleById(String id) throws PartyRoleNotFoundException{
		PartyRole partyRole = partyRoleRepo.findById(id).orElseThrow(() -> new PartyRoleNotFoundException("The Party role is not found for the id: "+id));
		return partyRole;
	}

	public PartyRole updatePartyRole(String id,PartyRole partyRole) throws PartyRoleNotFoundException {
		partyRoleRepo.findById(id).orElseThrow(() -> new PartyRoleNotFoundException("The Party role is not found for the id: "+id));
		partyRole.setIsActive(true);;
		
		Notification notification = new Notification();
		notification.setEventTime(LocalDateTime.now());
		notification.setEventType("PartyRoleStateChangeEvent");
		notification.setEvent(partyRole);

		notificationService.triggerEvent(notification);
		return partyRoleRepo.save(partyRole);
	}

	/**
	 * This method deletes the Party role
	 * @param id
	 * @param mergePatchDocument
	 * @throws PartyRoleNotFoundException 
	 */
	public void deletePartyRole(String id) throws PartyRoleNotFoundException {
		PartyRole partyRole = partyRoleRepo.findById(id).orElseThrow(() -> new PartyRoleNotFoundException("The Party role is not found for the id: "+id));
		partyRole.setIsActive(false);
		
		Notification notification = new Notification();
		notification.setEventTime(LocalDateTime.now());
		notification.setEventType("PartyRoleDeleteEvent");
		notification.setEvent(partyRole);

		partyRoleRepo.save(partyRole);

		notificationService.triggerEvent(notification);
	}
	
	/**
	 * Convert Java bean to JSON document, apply the patch to JSON document and then
	 * convert resulted JSON document to Java bean
	 * 
	 * @param mergePatch
	 * @param targetBean
	 * @param beanClass
	 * @return
	 */
	public PartyRole mergePatch(JsonMergePatch mergePatch, PartyRole targetBean, Class<PartyRole> beanClass) {

		JsonValue target = mapper.convertValue(targetBean, JsonValue.class);
		JsonValue patched = mergePatch.apply(target);

		return mapper.convertValue(patched, beanClass);
	}

	/**
	 * Patch the Party role
	 * @param id
	 * @param mergePatchDocument
	 * @throws PartyRoleNotFoundException 
	 */
	public void mergePartyRole(String id, JsonMergePatch mergePatchDocument) throws PartyRoleNotFoundException {
		// TODO Auto-generated method stub
		PartyRole partyRole = partyRoleRepo.findById(id).orElseThrow(() -> new PartyRoleNotFoundException("The Party role is not found for the id: "+id));

		PartyRole updatedMergePatch = mergePatch(mergePatchDocument, partyRole, PartyRole.class);
		PartyRole newPartyRole = partyRoleRepo.save(updatedMergePatch);

		Notification notification = new Notification();
		notification.setEventTime(LocalDateTime.now());
		notification.setEventType("PartyRoleAttributeValueChangeEvent");
		notification.setEvent(newPartyRole);

		notificationService.triggerEvent(notification);
	}

	/*
	 * public PartyRole updatePartyRoleByFields(String id, Map<String, Object>
	 * fields) { PartyRole partyRole = partyRoleRepo.findById(id).orElseThrow(() ->
	 * new IllegalArgumentException("The Party role is not found for the id: "+id));
	 *
	 * fields.forEach((key,value) -> { Field field =
	 * ReflectionUtils.findField(PartyRole.class,key);
	 *
	 * if(field.getName().equals("id") || field.getName().equals("href")) { throw
	 * new
	 * RuntimeException("The fields id and href are non patchable attributes for the id: "
	 * +id); }
	 *
	 * field.setAccessible(true); ReflectionUtils.setField(field, partyRole, value);
	 * }); return partyRoleRepo.save(partyRole); }
	 */
}
