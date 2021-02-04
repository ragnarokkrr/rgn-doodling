package ragna.reactor;

import java.util.function.BiFunction;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Zip {
  public static void main(String[] args) {
    //
    var pessoa = Pessoa.builder().code("1").code2("2").nome("Ze").build();

    var block =
        Mono.zip(buscarCode(pessoa), buscarCode2(pessoa), atualizarPessoa(pessoa))
            //.map(a -> a)
            .log()
            .flatMap(Zip::aplicar1)
            .flatMap(Zip::aplicar2)
            .flatMap(Zip::save)
            .block();
    System.out.println("ok---> " + block);
  }

  static Mono<Pessoa> save(Pessoa pessoa) {
    System.out.println("Save " + pessoa);
    return Mono.just(pessoa);
  }

  static Mono<Pessoa> aplicar1(Pessoa pessoa) {
    pessoa.setNome(pessoa.getNome() + " - aplicar1");
    return Mono.just(pessoa);
  }

  static Mono<Pessoa> aplicar2(Pessoa pessoa) {
    pessoa.setNome(pessoa.getNome() + " - aplicar2");
    return Mono.just(pessoa);
  }

  static Mono<String> buscarCode(Pessoa pessoa) {
    System.out.println("buscar Code");
    return Mono.just("Code: " + pessoa.getCode());
  }

  static Mono<String> buscarCode2(Pessoa pessoa) {
    System.out.println("buscar Code2");
    return Mono.just("Code2: " + pessoa.getCode2());
  }

  private static BiFunction<String, String, Pessoa> atualizarPessoa(Pessoa pessoa) {
    System.out.println("atualizarPessoa");
    return (code, code2) -> {
      System.out.println("atualizarPessoa Closure");
      pessoa.setCode(code);
      pessoa.setCode2(code2);
      return pessoa;
    };
  }

  @Data
  @Builder
  private static class Pessoa {
    private String code;
    private String code2;
    private String nome;
  }
}
