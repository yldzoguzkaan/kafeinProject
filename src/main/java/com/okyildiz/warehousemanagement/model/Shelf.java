package com.okyildiz.warehousemanagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
public class Shelf extends BaseEntityClass{
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "warehouseId")
	private Warehouse warehouse;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shelf")
	private List<Item> items = new ArrayList<Item>();
}
