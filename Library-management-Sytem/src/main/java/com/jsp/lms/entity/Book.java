package com.jsp.lms.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable{
	private int id;
	private String title;
	private String aurthor;
	private double price;
	private LocalDate published_date;
	private LocalDate issue_date;
	private Status status;
	private int libId;
	
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
	public String getAurthor() {
		return aurthor;
	}
	public void setAurthor(String aurthor) {
		this.aurthor = aurthor;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LocalDate getPublished_date() {
		return published_date;
	}
	public void setPublished_date(LocalDate published_date) {
		this.published_date = published_date;
	}
	public LocalDate getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(LocalDate issue_date) {
		this.issue_date = issue_date;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getLibId() {
		return libId;
	}
	public void setLibId(int libId) {
		this.libId = libId;
	}
	@Override
	public String toString() {
		return "Book --> \n id == " + id + "\n title == " + title + "\n aurthor == " + aurthor + "\n price == " + price + "\n published_date == "
				+ published_date + "\n issue_date == " + issue_date + "\n status == " + status+"\n" ;
	}
	
	
		
}
