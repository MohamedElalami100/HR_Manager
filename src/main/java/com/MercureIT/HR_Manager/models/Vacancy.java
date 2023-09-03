package com.MercureIT.HR_Manager.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Vacancy {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
		
	@ManyToOne
	@JoinColumn(name="jobtitleid", insertable=false, updatable=false)
	private JobTitle jobTitle;
	private Integer jobtitleid;
	
	@ManyToOne
	@JoinColumn(name="hiringmanagerid", insertable=false, updatable=false)
	private Employee hiringManager;
	private Integer hiringmanagerid;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JobTitle getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Integer getJobtitleid() {
		return jobtitleid;
	}

	public void setJobtitleid(Integer jobtitleid) {
		this.jobtitleid = jobtitleid;
	}

	public Employee getHiringManager() {
		return hiringManager;
	}

	public void setHiringManager(Employee hiringManager) {
		this.hiringManager = hiringManager;
	}

	public Integer getHiringmanagerid() {
		return hiringmanagerid;
	}

	public void setHiringmanagerid(Integer hiringmanagerid) {
		this.hiringmanagerid = hiringmanagerid;
	}

	
}
