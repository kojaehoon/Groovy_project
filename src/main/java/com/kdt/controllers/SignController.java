package com.kdt.controllers;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.dto.Sign_documentDTO;
import com.kdt.services.Sign_documentService;

@RestController
@RequestMapping("/api/signlist")
public class SignController {

	@Autowired
	Sign_documentService signservice;

	@PostMapping
	public ResponseEntity<String> post(Sign_documentDTO dto, MultipartFile[] files) throws Exception {

		String upload = "c:/uploads";
		File uploadPath = new File(upload);
		if (!uploadPath.exists()) {
			uploadPath.mkdir();
		}

		for (MultipartFile file : files) {
			String oriName = file.getOriginalFilename();
			String sysName = UUID.randomUUID() + "_" + oriName;
			file.transferTo(new File(uploadPath + "/" + sysName));
		}
		
		String writer = dto.getWriter();
		String document_type = dto.getDocument_type();
		String contents = dto.getContents();
		String recipient = dto.getRecipient();
		int accept = dto.getAccept();
		String title = dto.getTitle();
		
		System.out.println(writer + document_type + title + contents + recipient);
		
		signservice.insert(dto);
		return ResponseEntity.ok("완전 성공!"); // 클라이언트에게 http 응답코드 200번대 반환
	}
}
