package com.library.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book implements Serializable{

	private static final long serialVersionUID = 6705295136012658285L;

	private Long id;

	private String title;

	private String description;

	private Integer yearOfPublished;

	private Boolean available;

	private Author author;

	private Category category;
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: " + getId());
		sb.append(" - TITLE: " + getTitle());
		sb.append(" - CATEGORY: " + getCategory());
		sb.append(" - YEAR OF PUBLISHED: " + getYearOfPublished());
		sb.append(" - AVAILABLE: " + getAvailable());
		sb.append(" - AUTHOR: " + getAuthor());
		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getYearOfPublished() {
		return yearOfPublished;
	}

	public void setYearOfPublished(Integer yearOfPublished) {
		this.yearOfPublished = yearOfPublished;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
