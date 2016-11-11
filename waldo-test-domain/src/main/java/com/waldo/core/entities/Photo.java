package com.waldo.core.entities;

import java.io.Serializable;

/**
 * Entity that represents the photo.
 * 
 * Is serializable to can be sended to other remote systems or being manipulated by persistence engines or indexers
 * 
 * @author Gonzalo
 *
 */
public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer size;
	private String key;
	
	/*
	 * This should be a LocalDateTime, but like I will index maybe I could need to convert again to string
	 */
	private String time;

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
