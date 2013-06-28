package com.library.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Category {

	private Long id;

	private String type;
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: "+getId()).append(" - Type: "+getType());
		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
