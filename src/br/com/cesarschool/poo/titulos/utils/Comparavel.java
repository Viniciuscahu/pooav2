package br.com.cesarschool.poo.titulos.utils;

public interface Comparavel {
    /**
     * Compara o objeto atual com outro objeto recebido como parâmetro.
     *
     * @param c o objeto a ser comparado.
     * @return valor maior que 0 se o objeto atual for maior, menor que 0 se for menor,
     *         e 0 se forem iguais.
     */
    int comparar(Comparavel c);
}
