package com.rozagerardosamples.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rozagerardosamples.persistence.model.Foo;

public interface IFooRepository extends PagingAndSortingRepository<Foo, Long>, CrudRepository<Foo, Long> {

}
