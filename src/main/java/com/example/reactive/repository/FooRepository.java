package com.example.reactive.repository;

import com.example.reactive.model.Foo;
import reactor.core.publisher.Flux;

public interface FooRepository {
  Flux<Foo> getAllFoos();
}
