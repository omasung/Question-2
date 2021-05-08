package com.test.backend.model;

public class Header {

	private String appKey;
    private Long timeStamp;
    private String hashed;
    
	public Header(String appKey, Long timeStamp, String hashed) {
		
		super();
		this.appKey = appKey;
		this.timeStamp = timeStamp;
		this.hashed = hashed;
		
	}

	public Header () {
		
	}	
	
	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getHashed() {
		return hashed;
	}

	public void setHashed(String hashed) {
		this.hashed = hashed;
	}
	
}
