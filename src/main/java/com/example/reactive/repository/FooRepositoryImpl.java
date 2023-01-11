package com.example.reactive.repository;

import com.example.reactive.model.Foo;
import java.time.Duration;
import java.util.List;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class FooRepositoryImpl implements FooRepository{

  @Override
  public Flux<Foo> getAllFoos() {
    return Flux.interval(Duration.ofSeconds(1))
        .map(this::generateFoo)
        .flatMapIterable(x -> x);
  }

  private List<Foo> generateFoo(long interval){
    return List.of(new Foo(interval, "name " + interval));
  }
}
