package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;

import java.time.LocalDateTime;

public class MediatorOperacao {

    private final MediatorAcao mediatorAcao = MediatorAcao.getMediatorAcao();
    private final MediatorTituloDivida mediatorTituloDivida = MediatorTituloDivida.getMediatorTituloDivida();
    private final MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getMediatorEntidadeOperadora();
    private final RepositorioTransacao repositorioTransacao = new RepositorioTransacao();

    private static MediatorOperacao mediatorOperacao;

    private MediatorOperacao() {};

    public static MediatorOperacao getMediatorOperacao() {
        if (mediatorOperacao == null) {
            mediatorOperacao = new MediatorOperacao();
        }

        return mediatorOperacao;
    }

    public String realizarOperacao(boolean ehAcao, int entidadeCredito, int idEntidadeDebito, int idAcaoOuTitulo, double valor) {
        if(valor <= 0) {
            return "Valor inválido";
        }

        EntidadeOperadora credito = mediatorEntidadeOperadora.buscar(entidadeCredito);

        if(credito == null) {
            return "Entidade crédito inexistente";
        }

        EntidadeOperadora debito = mediatorEntidadeOperadora.buscar(idEntidadeDebito);

        if (debito == null) {
            return "Entidade débito inexistente";
        }

        if (ehAcao && (!credito.getAutorizadoAcao() || !debito.getAutorizadoAcao())) {
            return credito.getAutorizadoAcao() ? "Entidade de débito não autorizada para ação" : "Entidade de crédito não autorizada para ação";
        }

        Acao acao = ehAcao ? mediatorAcao.buscar(idAcaoOuTitulo) : null;
        TituloDivida titulo = ehAcao ? null : mediatorTituloDivida.buscar(idAcaoOuTitulo);

        if (ehAcao) {
            if (debito.getSaldoAcao() < valor) {
                return "Saldo da entidade débito insuficiente";
            }
        } else {
            if (debito.getSaldoTituloDivida() < valor) {
                return "Saldo da entidade débito insuficiente";
            }
        }

        if (ehAcao) {
            if (acao.getValorUnitario() > valor) {
                return "Valor da operação e menor do que o valor unitário da ação";
            }
        }

        double valorOperacao = ehAcao ? valor : titulo.calcularPrecoTransacao(valor);

        if (ehAcao) {
            credito.creditarSaldoAcao(valorOperacao);
        } else {
            credito.creditarSaldoTituloDivida(valorOperacao);
        }

        if (ehAcao) {
            debito.debitarSaldoAcao(valorOperacao);
        } else {
            debito.debitarSaldoTituloDivida(valorOperacao);
        }

        String altecaoCredito = mediatorEntidadeOperadora.alterar(credito);

        if (altecaoCredito != null) {
            return altecaoCredito;
        }

        String alteracaoDebito = mediatorEntidadeOperadora.alterar(debito);

        if (alteracaoDebito != null) {
            return alteracaoDebito;
        }

        Transacao transacao = new Transacao(credito, debito, acao, titulo, valorOperacao, LocalDateTime.now());

        repositorioTransacao.incluir(transacao);

        return "Operação realizada com sucesso";
    }
}
