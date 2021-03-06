package com.example.share.Entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@Entity
@Table(name = "Groups")
public class Groups implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1422641164637198070L;
	@Column(name="Group_name",nullable=false)
	private String grp_name;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Group_id")
	private long id;
	@Lob
    @Column(name = "Group_icon", length = Integer.MAX_VALUE, nullable = false)
	private byte[] grp_icon;
	@Column(name="Group_desc",nullable=false)
	private String grp_desc;
	@Column(name="Group_capacity",nullable=false)
	private int capacity;
	@OneToMany(targetEntity=Grp_participantss.class, mappedBy="grpId",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<Grp_participantss> grp_part;
	@OneToMany(targetEntity=GroupAdmin.class, mappedBy="grpId",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<GroupAdmin> grp_admn;
	@OneToMany(targetEntity=LeftGroup.class, mappedBy="groupsleft",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
   private Collection<LeftGroup> lefts;

	 @ManyToMany(mappedBy="groups")
	 private Collection<Messages> messages;

	@Transient
    public String getPhotosImagePath() {
        if (grp_icon == null) return null;
         System.out.println("picha");
        return "img1/GrpIcons/" +id+"/"+grp_icon;
    }


	public Collection<LeftGroup> getLefts() {
		return lefts;
	}

	public void setLefts(Collection<LeftGroup> lefts) {
		this.lefts = lefts;
	}

	public String getGrp_name() {
		return grp_name;
	}

	public void setGrp_name(String grp_name) {
		this.grp_name = grp_name;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public byte[] getGrp_icon() {
		return grp_icon;
	}

	public void setGrp_icon(byte[] grp_icon) {
		this.grp_icon = grp_icon;
	}

	public String getGrp_desc() {
		return grp_desc;
	}

	public void setGrp_desc(String grp_desc) {
		this.grp_desc = grp_desc;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Collection<Messages> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Messages> messages) {
		this.messages = messages;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	
}
