package br.com.cesarschool.poo.titulos.entidades;

import br.gov.cesarschool.poo.daogenerico.Entidade;

import java.time.LocalDate;

public class Ativo extends Entidade {

    // Atributos
    private int identificador;
    private String nome;
    private LocalDate dataValidade;

    // Construtor
    public Ativo(int identificador, String nome, LocalDate dataValidade) {
        this.identificador = identificador;
        this.nome = nome;
        this.dataValidade = dataValidade;
    }

    // Getters e Setters
    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }
    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getIdUnico() {
        return String.valueOf(this.identificador);
    }
}
