package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private String funcao;

    public String getFuncao() {
        return funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    // Construtor
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);

        this.salario = salario;
        this.funcao = funcao;
    }

    public void aplicarAumento(double percentual) {
        salario = salario.add(
                salario.multiply(
                        new BigDecimal(percentual).divide(new BigDecimal(100))
                )
        );
    }

    // 3.3 Imprimir os funcionários no formato correto
    @Override
    public String toString() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
            symbols.setGroupingSeparator('.');
            symbols.setDecimalSeparator(',');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("#,###.##", symbols);
        return nome + " (" + dataNascimento.format(formatter) + " R$" + df.format(salario) + ").";
    }
}

