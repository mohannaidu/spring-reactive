package com.example.reactive.controller;

import com.example.reactive.model.Foo;
import com.example.reactive.repository.FooRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/foo")
public class FooController {

  private final FooRepository fooRepository;

  public FooController(FooRepository fooRepository) {
    this.fooRepository = fooRepository;
  }

  @GetMapping(path = "/stream",
      produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Foo> getFoo() {
    return fooRepository.getAllFoos();
  }
}