package com.gustavo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.gustavo.model.Categorias;

@Repository
//public interface CategoriasRepository extends CrudRepository<Categorias, Integer> {

public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {

}
