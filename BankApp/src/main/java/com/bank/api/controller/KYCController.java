package com.bank.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.model.KYC;
import com.bank.api.model.Log;
import com.bank.api.model.User;
import com.bank.api.repository.KYCRepository;
import com.bank.api.repository.LogRepository;
import com.bank.api.repository.UserRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class KYCController {
	@Autowired
	private KYCRepository kycRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LogRepository logRepository;
	
	
	
	@PutMapping("/kyc/post/{uid}")
	public KYC postKYC(@RequestBody KYC kyc,@PathVariable("uid")Long uid) {
		User user=userRepository.getById(uid);
		user.setAddress(kyc.getAddress());
		user.setMobileNo(kyc.getMobileNo());
		userRepository.save(user);
		kyc.setUser(user);
	
		Log log=new Log();
		log.setOperation(user.getName()+" updated KYC");
		log.setTimestamp(LocalDate.now());
		log.setUser(user);
		logRepository.save(log);
		return kycRepository.save(kyc);	
		
	}
	
	@GetMapping("/kyc/{uid}")
	public List<KYC> getKycByUserId(@PathVariable("uid")Long uid) {
		return kycRepository.findByUserUserId(uid);
		
	}

}
