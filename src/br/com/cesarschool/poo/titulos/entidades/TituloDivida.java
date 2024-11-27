package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDate;

public class TituloDivida extends Ativo{
    // Atributos
    private double taxaJuros;

    // Construtor
    public TituloDivida(int identificador, String nome, LocalDate dataValidade, double taxaJuros) {
        super(identificador, nome, dataValidade);
        this.taxaJuros = taxaJuros;
    }

    // Getters e Setters
    public double getTaxaJuros() {
        return taxaJuros;
    }
    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    // Metodos
    public double calcularPrecoTransacao(double montante) {
        return montante * (1 - taxaJuros/100.0);
    }
}
