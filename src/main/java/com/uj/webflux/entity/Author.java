package com.uj.webflux.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Author {

	@Id
	@Column(name = "author_id")
	private Integer authorId;

	@Column(name = "author_name")
	private String authorName;

	public Author() {

	}

	public Author(Integer authorId, String authorName) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + "]";
	}
	
	

}
