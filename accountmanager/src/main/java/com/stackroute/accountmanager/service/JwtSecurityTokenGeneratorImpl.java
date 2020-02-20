package com.stackroute.accountmanager.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.accountmanager.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {

	
	
	public JwtSecurityTokenGeneratorImpl() {

	}

	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken = "";

		jwtToken = Jwts.builder()
					.setSubject(user.getUserId())
					.setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretkey")
					.compact();

		Map<String, String> map = new HashMap<>();
        System.out.println(jwtToken);		
        map.put("token", jwtToken);
		map.put("message", "Logged in successfully");
		return map;
	}


}
