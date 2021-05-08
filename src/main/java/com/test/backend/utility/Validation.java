package com.test.backend.utility;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Validation {

	public Boolean validateInput(String input) {

		Boolean bool = false;

		if (input.matches("[0-9]+") == true) {

			bool = true;

		}

		return bool;
	}

}
