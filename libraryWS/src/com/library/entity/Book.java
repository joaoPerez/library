package com.library.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.ForeignKey;

@XmlRootElement
@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, insertable = true, updatable = false)
	private int id;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	private String description;
	
	@Column(length = 4, nullable = false)
	private Integer yearOfPublished;
	
	private Boolean available;
	
	@ManyToOne(cascade = { CascadeType.PERSIST }, targetEntity = Author.class)
	@JoinColumn(name = "author_id")
	@ForeignKey(name = "FK_BOOK_AUTHOR_ID")
	private Author author;
	
	@ManyToOne(cascade = { CascadeType.PERSIST }, targetEntity = Category.class)
	@JoinColumn(name = "category_id")
	@ForeignKey(name = "FK_BOOK_CATEGORY_ID")
	private Category category;
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: "+getId());
		sb.append(" - TITLE: "+getTitle());
		sb.append(" - CATEGORY: "+getCategory());
		sb.append(" - YEAR OF PUBLISHED: "+getYearOfPublished());
		sb.append(" - AVAILABLE: "+getAvailable());
		sb.append(" - AUTHOR: "+getAuthor());
		return sb.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public void setCategor(Category category) {
		this.category = category;
	}
}
