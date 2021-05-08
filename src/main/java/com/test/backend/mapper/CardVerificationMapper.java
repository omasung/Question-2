package com.test.backend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.backend.dao.SchemeDAO;
import com.test.backend.dto.CardVerificationDTO;
import com.test.backend.model.Scheme;

@Service
@Transactional
public class CardVerificationMapper {

	@Autowired private SchemeDAO schemeDAO;

	public CardVerificationDTO cardVerification(String pan) {

			Scheme scheme = null;
			scheme = schemeDAO.verifyCard(pan);
			CardVerificationDTO cardVerificationDTO = new CardVerificationDTO();
	
			if (scheme != null) {
	
				cardVerificationDTO.setScheme(scheme.getScheme());
				cardVerificationDTO.setType(scheme.getType());
				cardVerificationDTO.setBank(scheme.getBank());
	
			} else {
				
				cardVerificationDTO = null;
				
			}

		return cardVerificationDTO;
	}

}
