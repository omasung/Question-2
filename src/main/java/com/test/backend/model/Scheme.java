package com.test.backend.model;

public class Scheme {

    private String scheme;
    private String type;
    private String bank;
    private String pan;
    private Integer hitCount;
    
	public Scheme(String scheme, String type, String bank, String pan, Integer hitCount) {
		
		super();
		this.scheme = scheme;
		this.type = type;
		this.bank = bank;
		this.pan = pan;
		this.hitCount = hitCount;
		
	}

	public Scheme () {
		
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

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Integer getHitCount() {
		return hitCount;
	}

	public void setHitCount(Integer hitCount) {
		this.hitCount = hitCount;
	}
    	
}
