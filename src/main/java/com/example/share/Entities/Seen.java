package com.example.share.Entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;


import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "seen")
public class Seen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Seen_id")
	private long id;
	
	@ManyToOne()
	@JoinColumn(name="std_id", referencedColumnName = "st_id")    
	private Student studentsee;
	@ManyToOne()
	@JoinColumn(name="msg_id", referencedColumnName = "msg_id")    
	private Messages msg;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Student getStudentsee() {
		return studentsee;
	}
	public void setStudentsee(Student studentsee) {
		this.studentsee = studentsee;
	}
	public Messages getMsg() {
		return msg;
	}
	public void setMsg(Messages msg) {
		this.msg = msg;
	}

	
}
