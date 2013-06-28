package com.library.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Author {

	private Long id;

	private String name;
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: "+getId()).append(" - NAME: "+getName());
		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
