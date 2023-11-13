package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdt.dto.Realtime_NotificationsDTO;
import com.kdt.services.Realtime_NotificationsService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/realtime_notification")
public class Realtime_NotificationsController {
	@Autowired
	Realtime_NotificationsService rservice;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping
	public ResponseEntity<List<Realtime_NotificationsDTO>> selectUnreadNotifications(){
		String id = (String) session.getAttribute("loginID");
		List<Realtime_NotificationsDTO> list = rservice.selectUnreadNotifications(id);
		return ResponseEntity.ok(list);
	}
	
	@PutMapping
	public ResponseEntity<Void> updateReadStatus(){
		String id = (String) session.getAttribute("loginID");
		rservice.updateReadStatus(id);
		return ResponseEntity.ok().build();
	}
	
}
