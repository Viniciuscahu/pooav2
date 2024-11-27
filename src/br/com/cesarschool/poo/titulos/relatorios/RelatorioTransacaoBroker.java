package br.com.cesarschool.poo.titulos.relatorios;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;
import br.com.cesarschool.poo.titulos.utils.ComparadorPorDataHora;
import br.com.cesarschool.poo.titulos.utils.ComparadorTransacaoPorNomeCredora;
import br.com.cesarschool.poo.titulos.utils.Ordenador;
import br.gov.cesarschool.poo.daogenerico.Entidade;

public class RelatorioTransacaoBroker {
    RepositorioTransacao repositorioTransacao = new RepositorioTransacao();


    /**
     * Retorna um relatório de transações ordenado pelo nome da entidade credora.
     *
     * @return Array de transações ordenadas.
     */
    public Transacao[] relatorioTransacaoPorNomeEntidadeCredora() {
        Entidade[] entidades = repositorioTransacao.buscarTodos();
        Transacao[] transacoes = converterParaTransacoes(entidades);

        ComparadorTransacaoPorNomeCredora comparador = new ComparadorTransacaoPorNomeCredora();
        Ordenador.ordenar(transacoes, comparador);

        return transacoes;
    }

    public Transacao[] relatorioTransacaoPorDataHora() {
        Entidade[] entidades = repositorioTransacao.buscarTodos();
        Transacao[] transacoes = converterParaTransacoes(entidades);
        ComparadorPorDataHora comparador = new ComparadorPorDataHora();
        Ordenador.ordenar(transacoes, comparador);

        return transacoes;
    }

    private Transacao[] converterParaTransacoes(Entidade[] entidades) {
        return java.util.Arrays.stream(entidades).filter(Transacao.class::isInstance).map(Transacao.class::cast).toArray(Transacao[]::new);
    }
}
