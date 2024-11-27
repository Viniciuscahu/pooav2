package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDate;

public class Acao extends Ativo{

    // Atributos
    private double valorUnitario;

    // Construtor
    public Acao(int identificador, String nome, LocalDate dataValidade, double valorUnitario) {
        super(identificador, nome, dataValidade);
        this.valorUnitario = valorUnitario;
    }

    // Getters e Setters
    public double getValorUnitario() {
        return valorUnitario;
    }
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    // Metodos
    public double calcularPrecoTransacao(double montante) {
        return montante * valorUnitario;
    }
}
