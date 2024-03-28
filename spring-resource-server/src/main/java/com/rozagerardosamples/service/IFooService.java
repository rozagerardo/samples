package com.rozagerardosamples.service;

import java.util.Optional;

import com.rozagerardosamples.persistence.model.Foo;

public interface IFooService {

    Optional<Foo> findById(Long id);

    Foo save(Foo foo);

    Iterable<Foo> findAll();

}
