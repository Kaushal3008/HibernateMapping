package com.ManyToMany;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class ProjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectId;
	private String project;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "develop_project",joinColumns = {@JoinColumn(name="project_id")},inverseJoinColumns = {@JoinColumn(name="develop_id")})
	
	private List<DeveloperEntity> developers;
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public List<DeveloperEntity> getDevelopers() {
		return developers;
	}
	public void setDevelopers(List<DeveloperEntity> developers) {
		this.developers = developers;
	}
	
	
}
