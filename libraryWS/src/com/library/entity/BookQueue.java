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
@Table(name = "bookQueue")
public class BookQueue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, insertable = true, updatable = false)
	private int id;
	
	@ManyToOne(cascade = { CascadeType.PERSIST }, targetEntity = User.class)
	@JoinColumn(name = "user_id")
	@ForeignKey(name = "FK_BOOKQUEUE_AUTHOR_ID")
	private User user;
	
	@ManyToOne(cascade = { CascadeType.PERSIST }, targetEntity = Book.class)
	@JoinColumn(name = "book_id")
	@ForeignKey(name = "FK_BOOKQUEUE_BOOK_ID")
	private Book book;
	
	private Boolean renting;
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: "+getId()).append(" - USER: "+getUser()).append(" - BOOK: "+getBook());
		return sb.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Boolean getRenting() {
		return renting;
	}

	public void setRenting(Boolean renting) {
		this.renting = renting;
	}
}
