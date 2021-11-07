package com.okyildiz.warehousemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.okyildiz.warehousemanagement.model.Shelf;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Integer> {

}
