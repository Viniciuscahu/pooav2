package br.com.cesarschool.poo.titulos.entidades;

import br.gov.cesarschool.poo.daogenerico.Entidade;

public class EntidadeOperadora extends Entidade {

    // Atributos
    private long identificador;
    private String nome;
    private boolean autorizadoAcao;
    private double saldoAcao;
    private double saldoTituloDivida;

    // Construtor
    public EntidadeOperadora(long identificador, String nome, boolean autorizadoAcao) {
        this.identificador = identificador;
        this.nome = nome;
        this.autorizadoAcao = autorizadoAcao;
    }

    public EntidadeOperadora(long identificador, String nome, double autorizadoAcao) {
        super();
        this.identificador = identificador;
        this.nome = nome;
    }

    // Getters e Setters
    public long getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getAutorizadoAcao() {
        return autorizadoAcao;
    }
    public void setAutorizadoAcao(boolean autorizadoAcao) {
        this.autorizadoAcao = autorizadoAcao;
    }

    public double getSaldoAcao() {
        return saldoAcao;
    }

    public double getSaldoTituloDivida() {
        return saldoTituloDivida;
    }

    // Metodos
    public void creditarSaldoAcao(double valor) {
        saldoAcao += valor;
    }

    public void debitarSaldoAcao(double valor) {
        saldoAcao -= valor;
    }

    public void creditarSaldoTituloDivida(double valor) {
        saldoTituloDivida += valor;
    }

    public void debitarSaldoTituloDivida(double valor) {
        saldoTituloDivida -= valor;
    }

    public String getIdUnico() {
        return String.valueOf(this.identificador);
    }
}
