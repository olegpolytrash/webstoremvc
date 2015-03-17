package com.webstoremvc.database.model;

import javax.persistence.*;

/**
 * Hibernate entity.
 * Represents the Commodity table in the database.
 */
@Entity
@Table(name="Commodity")
public class Commodity {
	@Id @GeneratedValue
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_category")
	private Category category;
	
	@Column(name="price")
	private int price;
	
	@Column(name="name")
	private String name;
	
	@Column(name="image_path")
	private String image_path;

	public Commodity() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((image_path == null) ? 0 : image_path.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price;
		return result;
	}



	/* (non-Javadoc)
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
		Commodity other = (Commodity) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id != other.id)
			return false;
		if (image_path == null) {
			if (other.image_path != null)
				return false;
		} else if (!image_path.equals(other.image_path))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		return true;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
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
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}



	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}



	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}



	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
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



	/**
	 * @return the image_path
	 */
	public String getImage_path() {
		return image_path;
	}



	/**
	 * @param image_path the image_path to set
	 */
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	
	
	
}
