package org.sid.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {

	@Id
	private String role;
	@OneToMany(mappedBy="role",fetch=FetchType.LAZY)
    private Collection<Relation> relations;
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Collection<Relation> getRelations() {
		return relations;
	}

	public void setRelations(Collection<Relation> relations) {
		this.relations = relations;
	}

	public Role(String role) {
		super();
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
