package com.rozagerardosamples.service.impl;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rozagerardosamples.persistence.model.Foo;
import com.rozagerardosamples.persistence.repository.IFooRepository;
import com.rozagerardosamples.service.IFooService;

@Service
public class FooServiceImpl implements IFooService {

    private IFooRepository fooRepository;

    public FooServiceImpl(IFooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @Override
    public Optional<Foo> findById(Long id) {
        return fooRepository.findById(id);
    }

    @Override
    public Foo save(Foo foo) {
        if (Objects.isNull(foo.getId())) {
            foo.setDateCreated(LocalDate.now());
        }
        return fooRepository.save(foo);
    }

    @Override
    public Iterable<Foo> findAll() {
        return fooRepository.findAll();
    }
}
