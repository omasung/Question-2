package com.test.backend.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.backend.dao.SchemeDAO;
import com.test.backend.model.Scheme;

@Service
@Transactional
public class HitCountMapper {
	
	@Autowired private SchemeDAO schemeDAO;
	
	public Map<String, Integer> hitCount(int start, int limit) {
		
		List<Scheme> schemeList = schemeDAO.numberOfHits(start, limit);			
		Map<String, Integer> map = schemeList.stream().collect(Collectors.toMap(Scheme::getPan, Scheme::getHitCount));

		
		return map;
	}

}
