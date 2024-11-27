package br.com.cesarschool.poo.titulos.relatorios;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;
import br.com.cesarschool.poo.titulos.utils.ComparadorPorDataHora;
import br.com.cesarschool.poo.titulos.utils.ComparadorTransacaoPorNomeCredora;
import br.com.cesarschool.poo.titulos.utils.Ordenador;
import br.gov.cesarschool.poo.daogenerico.Entidade;

public class RelatorioTransacaoBroker {
    RepositorioTransacao repositorioTransacao = new RepositorioTransacao();

    public Transacao[] relatorioTransacaoPorNomeEntidadeCredora() {
        // Obtem todas as entidades
        Entidade[] entidades = repositorioTransacao.buscarTodos();

        // Converte entidades para Transacao, validando o tipo
        Transacao[] transacoes = converterParaTransacoes(entidades);

        ComparadorTransacaoPorNomeCredora comparador = new ComparadorTransacaoPorNomeCredora();
        Ordenador.ordenar(transacoes, comparador);

        return transacoes;
    }

    public Transacao[] relatorioTransacaoPorDataHora() {
        // Obtem todas as entidades
        Entidade[] entidades = repositorioTransacao.buscarTodos();

        // Converte entidades para Transacao, validando o tipo
        Transacao[] transacoes = converterParaTransacoes(entidades);

        // Ordena as transações pela data e hora
        ComparadorPorDataHora comparador = new ComparadorPorDataHora();
        Ordenador.ordenar(transacoes, comparador);

        return transacoes;
    }

    // Método auxiliar para converter Entidade[] para Transacao[]
    private Transacao[] converterParaTransacoes(Entidade[] entidades) {
        return java.util.Arrays.stream(entidades)
                .filter(Transacao.class::isInstance) // Filtra apenas objetos do tipo Transacao
                .map(Transacao.class::cast) // Faz o cast seguro para Transacao
                .toArray(Transacao[]::new); // Converte para um array de Transacao
    }
}
