package com.ManyToMany;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="developer")
public class DeveloperEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int developerId;
	private String name;
	@ManyToMany(mappedBy = "developers",cascade = CascadeType.ALL)
	private List<ProjectEntity> projects;
	public int getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ProjectEntity> getProjects() {
		return projects;
	}
	public void setProjects(List<ProjectEntity> projects) {
		this.projects = projects;
	}
	
	
}
