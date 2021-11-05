package com.okyildiz.warehousemanagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Warehouse extends BaseEntityClass{
	
	@Column
	private String name;
	
	@Column
	private String address;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "warehouse")
	private List<Shelf> shelfs = new ArrayList<Shelf>();
	
}
