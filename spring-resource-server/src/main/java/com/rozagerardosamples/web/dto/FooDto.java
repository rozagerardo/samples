package com.rozagerardosamples.web.dto;

import java.time.LocalDate;

public record FooDto(

    Long id,

    String name,

    LocalDate dateCreated) {

}
