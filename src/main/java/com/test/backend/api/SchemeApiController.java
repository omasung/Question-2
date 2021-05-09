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
import com.test.backend.test.SchemeTestDriver;
import com.test.backend.utility.HashSHA512;
import com.test.backend.utility.Validation;

@RestController
@RequestMapping(value = "/card-scheme")
public class SchemeApiController {

	@Autowired private CardVerificationMapper cardVerificationMapper;
	@Autowired private HitCountMapper hitCountMapper;
	@Autowired private SchemeDAO schemeDAO;
	@Autowired private SchemeTestDriver schemeTestDriver;
	@Autowired private HashSHA512 hashSHA512;
	@Autowired private Validation validation;

	@RequestMapping(value = "/verify/{pan}", method = { RequestMethod.GET }, produces = "application/json")
	public @ResponseBody Map<String, Object> schemeVerificationApi(@PathVariable String pan, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();

		String appKey = request.getHeader("appKey");
		String timeStamp = request.getHeader("timeStamp");
		String authorization = request.getHeader("authorization");

		if (appKey == null || timeStamp == null || authorization == null) {

			map.put("message", "invalid message request");

		} else {

			Boolean success = false;
			CardVerificationDTO cardVerificationDTO = cardVerificationMapper.cardVerification(pan);
			String myAuthorization = "3line" + " " + hashSHA512.getHashSHA512(appKey + timeStamp);

			if (!myAuthorization.equals(authorization)) {

				map.put("message", "invalid authorization key");

			} else if (cardVerificationDTO == null) {

				map.put("message", "card details not found");

			} else if (cardVerificationDTO != null) {

				success = true;

				map.put("success", success);
				map.put("payload", cardVerificationDTO);

			}

		}

		return map;

	}

	@RequestMapping(value = "/stats", method = { RequestMethod.GET }, produces = "application/json")
	public @ResponseBody Map<String, Object> schemeVerificationApi(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();

		String appKey = request.getHeader("appKey");
		String timeStamp = request.getHeader("timeStamp");
		String authorization = request.getHeader("authorization");

		String start = request.getParameter("start");
		String limit = request.getParameter("limit");

		if (appKey == null || timeStamp == null || authorization == null) {

			map.put("message", "invalid message request");

		} else {
			
			Boolean success = false;
			String myAuthorization = "3line" + " " + hashSHA512.getHashSHA512(appKey + timeStamp);

			if (!myAuthorization.equals(authorization)) {

				map.put("message", "invalid authorization key");

			} else if (validation.validateInput(start).equals(false) || validation.validateInput(limit).equals(false)) {

				map.put("message", "invalid start or limit value");

			} else if (Integer.parseInt(limit) > schemeDAO.schemeSize()) {

				map.put("message", "start or limit value is out of range");

			} else {

				Map<String, Integer> mapHitCount = hitCountMapper.hitCount(Integer.parseInt(start), Integer.parseInt(limit));

				success = true;

				map.put("success", success);
				map.put("start", start);
				map.put("limit", limit);
				map.put("size", limit);
				map.put("payload", mapHitCount);

			}

		}

		return map;

	}

	@RequestMapping(value = "/test/driver", method = { RequestMethod.GET }, produces = "application/json")
	public @ResponseBody Map<String, Object> schemeVerificationApi() {

		Map<String, Object> map = new HashMap<String, Object>();

		schemeTestDriver.testApiTestVerifyCard();
		schemeTestDriver.testApiTestHitCount();

		return map;

	}

}
