package com.test.backend.dto;

public class CardVerificationDTO {

    private String scheme;
    private String type;
    private String bank;
    
	public CardVerificationDTO(String scheme, String type, String bank) {
		
		super();
		this.scheme = scheme;
		this.type = type;
		this.bank = bank;
		
	}

	public CardVerificationDTO () {
		
	}
	
	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
}
