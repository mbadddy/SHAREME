package com.example.share.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Indexed;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;

@Entity
@Table(name = "student",uniqueConstraints=@UniqueConstraint(columnNames={"email"}))
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="st_id")
	
	private long id;
	@NotBlank(message = "first name must not be empty")
	@Size(min = 3,max = 15)
	@Column(name="st_f_name")
	private String first;
	@NotBlank(message = "last name must not be empty")
	@Size(min = 3,max = 15)
	@Column(name="st_l_name")
	private String last;
	@Column(name="email",nullable=false)
	@Email(message = "your email must be correct..")
	private String email;
	@NotBlank(message = "password must not blank")
	@Column(name="st_password",nullable=false,unique = true)
	private String password;
	@Max(35)
	@Min(17)
	@Column(name="st_age")
	private int age;
	@Column(name="st_photo",nullable = true,length = 64)
	private String photos;
	 @ManyToMany(mappedBy="stdto")
		private Collection<Messages> msgto;
	 @ManyToMany(mappedBy="stdfrom")
		private Collection<Messages> msgfrom;
	@ManyToOne()
	@JoinColumn(name="progr_id", referencedColumnName = "progr_id")    
	private Programme programme;
	
	@ManyToOne()
	@JoinColumn(name="perm_id", referencedColumnName = "permit_id")    
	private Permission permission;
	@OneToMany(targetEntity=Grp_participantss.class, mappedBy="stdId",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<Grp_participantss> grp_part;
	@OneToMany(targetEntity=GroupAdmin.class, mappedBy="stdId",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private Collection<GroupAdmin> grp_admin;

	@OneToMany(targetEntity=Requests.class, mappedBy="studentfrom",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	 private Collection<Requests> fromStd;
	@OneToMany(targetEntity=RequestFrom.class, mappedBy="std",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	 private Collection<RequestFrom> requestfrom;
	@OneToMany(targetEntity=RequestTo.class, mappedBy="std",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	 private Collection<RequestTo> requestto;
	@OneToMany(targetEntity=LeftGroup.class, mappedBy="studentleft",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	 private Collection<LeftGroup> lefts;
	@OneToMany(targetEntity=Seen.class, mappedBy="studentsee",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	 private Collection<Seen> msgsee;
	@OneToMany(targetEntity=Archieve.class, mappedBy="studentachiev",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	 private Collection<Archieve> msgsachieve;
	@OneToMany(targetEntity=Delete.class, mappedBy="studentdel",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	 private Collection<Delete> msgdel;
	@OneToMany(targetEntity=ClearMsgs.class, mappedBy="studentclear",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	 private Collection<ClearMsgs> msgclear;
	@OneToMany(targetEntity=Requests.class, mappedBy="studentTo",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	 private Collection<Requests> toStd;
	@OneToMany(targetEntity=Archieve.class, mappedBy="stdmsgs",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
 	private Collection<Archieve> achieved;
	@Transient
	private List<MultipartFile> files=new ArrayList<MultipartFile>();
	
	
	 @ManyToMany
		@JoinTable(
				name="std_courses",
				joinColumns= @JoinColumn(
						name="st_Id"),
				inverseJoinColumns=@JoinColumn(
						name="course_Id")
				  )
	 private Collection<Course> courses;
	 @ManyToMany
		@JoinTable(
				name="std_photos",
				joinColumns= @JoinColumn(
						name="st_Id"),
				inverseJoinColumns=@JoinColumn(
						name="img_id")
				  )
	 private Collection<ImageGallery> imagess;
	 
    public Collection<ImageGallery> getImagess() {
		return imagess;
	}

	public void setImagess(Collection<ImageGallery> imagess) {
		this.imagess = imagess;
	}

	 @ManyToMany(mappedBy="tagged")
		private Set<Files> file;
	 @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
		@JoinTable(
				name="std_Roles",
				joinColumns= @JoinColumn(
						name="st_Id"),
				inverseJoinColumns=@JoinColumn(
						name="ROle_Id")
				  )
	private Collection<Roles> roles;

	 

	public Student() {
		super();
	}

	public Student(String first,String photos, String last, String email, String password, int age,
			 Permission permission,
			 Programme programme, Collection<Course> courses,
			Collection<Roles> roles) {
		super();
		this.first = first;
		this.permission = permission;
		this.photos = photos;
		this.last = last;
		this.email = email;
		this.password = password;
		this.age = age;
		this.programme = programme;
		this.courses = courses;
		this.roles = roles;
	}
        public Student(Collection<Roles> roles) {
		super();
		this.roles = roles;
	}

	public Student(long id, @NotBlank(message = "first name must not be empty") @Size(min = 3, max = 15) String first,
			@NotBlank(message = "last name must not be empty") @Size(min = 3, max = 15) String last,
			Collection<Roles> roles) {
		super();
		this.id = id;
		this.first = first;
		this.last = last;
		this.roles = roles;
	}

	
	public Collection<Grp_participantss> getGrp_part() {
		return grp_part;
	}

	public void setGrp_part(Collection<Grp_participantss> grp_part) {
		this.grp_part = grp_part;
	}

	public Collection<GroupAdmin> getGrp_admin() {
		return grp_admin;
	}

	public void setGrp_admin(Collection<GroupAdmin> grp_admin) {
		this.grp_admin = grp_admin;
	}


	public Collection<RequestFrom> getRequestfrom() {
		return requestfrom;
	}

	public void setRequestfrom(Collection<RequestFrom> requestfrom) {
		this.requestfrom = requestfrom;
	}

	public Collection<RequestTo> getRequestto() {
		return requestto;
	}

	public void setRequestto(Collection<RequestTo> requestto) {
		this.requestto = requestto;
	}

	public Collection<Archieve> getAchieved() {
		return achieved;
	}

	public void setAchieved(Collection<Archieve> achieved) {
		this.achieved = achieved;
	}

	public Collection<Archieve> getMsgsachieve() {
		return msgsachieve;
	}

	public void setMsgsachieve(Collection<Archieve> msgsachieve) {
		this.msgsachieve = msgsachieve;
	}

	public Collection<Delete> getMsgdel() {
		return msgdel;
	}

	public void setMsgdel(Collection<Delete> msgdel) {
		this.msgdel = msgdel;
	}

	public Collection<ClearMsgs> getMsgclear() {
		return msgclear;
	}

	public void setMsgclear(Collection<ClearMsgs> msgclear) {
		this.msgclear = msgclear;
	}

	public Collection<Seen> getMsgsee() {
		return msgsee;
	}

	public void setMsgsee(Collection<Seen> msgsee) {
		this.msgsee = msgsee;
	}

	public Collection<LeftGroup> getLefts() {
		return lefts;
	}

	public void setLefts(Collection<LeftGroup> lefts) {
		this.lefts = lefts;
	}


	public Set<Files> getFile() {
		return file;
	}

	public void setFile(Set<Files> file) {
		this.file = file;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public Collection<Messages> getMsgfrom() {
		return msgfrom;
	}

	public void setMsgfrom(Collection<Messages> msgfrom) {
		this.msgfrom = msgfrom;
	}

	public Collection<Messages> getMsgto() {
		return msgto;
	}

	public void setMsgto(Collection<Messages> msgto) {
		this.msgto = msgto;
	}

	public Collection<Requests> getFromStd() {
		return fromStd;
	}

	public void setFromStd(Collection<Requests> fromStd) {
		this.fromStd = fromStd;
	}

	public Collection<Requests> getToStd() {
		return toStd;
	}

	public void setToStd(Collection<Requests> toStd) {
		this.toStd = toStd;
	}

	public String getPhotos() {
		return photos;
	}
	@Transient
    public String getPhotosImagePath() {
        if (photos == null) return null;
         System.out.println("picha");
        return "img1/userphotos/" +id+"/"+photos;
    }
	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public Collection<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Roles> roles) {
		this.roles = roles;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	public Programme getProgramme() {
		return programme;
	}

	public void setProgramme(Programme programme) {
		this.programme = programme;
	}

	public Collection<Course> getCourses() {
		return courses;
	}

	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}
	
	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", first=" + first + ", last=" + last + ", email=" + email + ", password="
				+ password + ", age=" + age +  ", university="+
				 ", programme=" + programme + ", courses=" + courses + 
				 "]";
	}
	 
	 
}
