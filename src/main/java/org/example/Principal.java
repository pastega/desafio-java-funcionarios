package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    // Inserindo todos os dados
    public static List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
        new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
        new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
        new Funcionario("Heloisa", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
        new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
        new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
        new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
        new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
        new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
        new Funcionario("Caio", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
        new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
    ));

    public static void main(String[] args) {

        // 3.2 Remover funcionário João da lista
        funcionarios.removeIf(f -> f.nome.equals("João"));

        // 3.3 Imprimir todos os funcionários no formato correto
        funcionarios.forEach(System.out::println);

        // 3.4 Aplicar um aumento de 10% ao salário de todos os funcionários
        funcionarios.forEach(f -> f.aplicarAumento(10));

        // 3.5 Agrupar os funcionários por função em um map
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(
                        Collectors.groupingBy(Funcionario::getFuncao)
                );

        // 3.6 Imprimir os funcionários agrupados por função
        System.out.println(funcionariosPorFuncao);

        // 3.7 Imprimir os funcionários que fazem aniversário no mês 10 e 12
        String funcionariosAniversario10ou12 = funcionarios.stream()
                .filter(f -> {
                    int mes = f.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .map(Funcionario::getNome)
                .collect(Collectors.joining(", "));

        System.out.println("Os funcionários que fazem aniversário no mes 10 ou 12 são: " + funcionariosAniversario10ou12);

        // 3.8 Imprimir o funcionário com maior idade
        Optional<Funcionario> maisVelho = funcionarios.stream()
                .reduce((f1, f2) -> f1.getDataNascimento().isBefore(f2.getDataNascimento()) ? f1 : f2);

        maisVelho.ifPresent(f -> {
            System.out.println("Funcionário mais velho: " + f.getNome() + " com idade: " + Period.between(f.getDataNascimento(), LocalDate.now()).getYears());
        });


        // 3.9 Imprimir a lista de funcionários por odem alfabética
        List<Funcionario> ordenado = funcionarios.stream()
                        .sorted(Comparator.comparing(Funcionario::getNome))
                        .toList();

        String ordem = ordenado.stream()
                        .map(Funcionario::getNome)
                                .collect(Collectors.joining(", "));
        System.out.println("Nome dos funcionários em ordem alfabética");
        System.out.println(ordem);

        BigDecimal salarioTotal = funcionarios.stream()
                        .map(Funcionario::getSalario)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("A soma de todos os salários é R$ " + salarioTotal);

        BigDecimal soma = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            soma = soma.add(f.getSalario());
        }

        System.out.println("Soma total dos salários: R$ " +soma);

        final BigDecimal salario_min = BigDecimal.valueOf(1212.00);

        Map<String, BigDecimal> funcionarioSalario = funcionarios.stream()
                .collect(Collectors.toMap(
                        Funcionario::getNome,
                f -> f.getSalario().divide(salario_min, 2, RoundingMode.HALF_UP))
                );

        funcionarioSalario.forEach((nome, salariosMinimos) -> {
            System.out.println(nome + " ganha " + salariosMinimos + " salários mínimos.");
        });

        BigDecimal salarioMin = new BigDecimal("1212.00");

        for (Funcionario f : funcionarios) {
            BigDecimal qtdSalarioMin = f.getSalario().divide(salarioMin, 2, RoundingMode.HALF_DOWN);
            System.out.println(f.getNome() + " ganha " + qtdSalarioMin + "salários mínimos");
        }
    }
}