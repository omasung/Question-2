package com.test.backend.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.backend.dao.SchemeDAO;
import com.test.backend.dto.CardVerificationDTO;
import com.test.backend.mapper.CardVerificationMapper;
import com.test.backend.mapper.HitCountMapper;
import com.test.backend.test.SchemTestDriver;
import com.test.backend.utility.HashSHA512;
import com.test.backend.utility.Validation;

@RestController
@RequestMapping(value = "/card-scheme")
public class SchemeApiController {

	@Autowired private CardVerificationMapper cardVerificationMapper;
	@Autowired private HitCountMapper hitCountMapper;
	@Autowired private SchemeDAO schemeDAO;
	@Autowired private SchemTestDriver schemTestDriver;
	@Autowired private HashSHA512 hashSHA512;
	@Autowired private Validation validation;

	@RequestMapping(value = "/verify/{pan}", method = { RequestMethod.GET }, produces = "application/json")
	public @ResponseBody Map<String, Object> schemeVerificationApi(@PathVariable String pan, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		String appKey = request.getHeader("appKey");
		String timeStamp = request.getHeader("timeStamp");
		String hashed = request.getHeader("hashed");

		Boolean success = false;
		CardVerificationDTO cardVerificationDTO = cardVerificationMapper.cardVerification(pan);
		
		if (appKey == null || timeStamp == null || !hashSHA512.getHashSHA512(appKey + timeStamp).equals(hashed)) {

			map.put("success", success);
			map.put("message", "invalid message request");
			
		} else if (cardVerificationDTO == null) {

			map.put("success", success);
			map.put("message", "card details not found");

		} else if (cardVerificationDTO != null) {
			
			success = true;

			map.put("success", success);
			map.put("payload", cardVerificationDTO);
			
		}	

		return map;

	}

	@RequestMapping(value = "/stats", method = { RequestMethod.GET }, produces = "application/json")
	public @ResponseBody Map<String, Object> schemeVerificationApi(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();

		Boolean success = false;
		
		String appKey = request.getHeader("appKey");
		String timeStamp = request.getHeader("timeStamp");
		String hashed = request.getHeader("hashed");
		
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");

		if (appKey == null || timeStamp == null || !hashSHA512.getHashSHA512(appKey + timeStamp).equals(hashed)) {

			map.put("success", success);
			map.put("message", "invalid message request");
			
		} else if (validation.validateInput(start).equals(false) || validation.validateInput(limit).equals(false)) {
			
			map.put("success", success);
			map.put("message", "your start or limit input is invalid");
			
		} else if (Integer.parseInt(limit) > schemeDAO.schemeSize()) {

			map.put("success", success);
			map.put("message", "your input is out of range");

		} else {

			Map<String, Integer> mapHitCount = hitCountMapper.hitCount(Integer.parseInt(start), Integer.parseInt(limit));

			success = true;

			map.put("success", success);
			map.put("start", start);
			map.put("limit", limit);
			map.put("size", limit);
			map.put("payload", mapHitCount);

		}

		return map;

	}
	
	@RequestMapping(value = "/test/driver", method = { RequestMethod.GET }, produces = "application/json")
	public @ResponseBody Map<String, Object> schemeVerificationApi() {

		Map<String, Object> map = new HashMap<String, Object>();

		
		schemTestDriver.testApiTestVerifyCard();
		schemTestDriver.testApiTestHitCount();

		return map;

	}	

}
