package com.webstoremvc.database.model;

import javax.persistence.*;

/**
 * Hibernate entity.
 * Represents the Category table in the database.
 */
@Entity
@Table(name="Category")
public class Category {
	@Id @GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	public Category() {
		super();
	}

	/**
	 *  Return the string representation of the entry.
	 * @see java.lang.Object#toString()
	 * @return the string representation of the entry.
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Return the hash code of the entry 
	 * @see java.lang.Object#hashCode()
	 * @return the hash code of the entry 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
