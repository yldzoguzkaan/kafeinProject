package com.okyildiz.warehousemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Item extends BaseEntityClass{

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "shelfId")
	private Shelf shelf;
	
	@Column
	private String barcode;
}
