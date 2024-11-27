package br.com.cesarschool.poo.titulos.utils;

public interface Comparador {
    /**
     * Compara dois objetos recebidos como parâmetros.
     *
     * @param c1 o primeiro objeto a ser comparado.
     * @param c2 o segundo objeto a ser comparado.
     * @return valor maior que 0 se o primeiro for maior, menor que 0 se o primeiro for menor,
     *         e 0 se forem iguais.
     */
    int comparar(Comparavel c1, Comparavel c2);
}
