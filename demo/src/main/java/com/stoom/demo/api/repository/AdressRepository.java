package com.stoom.demo.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stoom.demo.api.model.Adress;

@Repository
public interface AdressRepository extends CrudRepository<Adress, Long> {

	Iterable<Adress> findAll();

}

