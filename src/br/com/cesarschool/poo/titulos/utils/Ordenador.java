package br.com.cesarschool.poo.titulos.utils;

public class Ordenador {

    /**
     * Ordena um array de objetos `Comparavel` usando um `Comparador`.
     *
     * @param ents o array a ser ordenado.
     * @param comp o comparador que define o critério de ordenação.
     */
    public static void ordenar(Comparavel[] ents, Comparador comp) {
        for (int i = 0; i < ents.length - 1; i++) {
            for (int j = 0; j < ents.length - i - 1; j++) {
                if (comp.comparar(ents[j], ents[j + 1]) > 0) {
                    Comparavel temp = ents[j];
                    ents[j] = ents[j + 1];
                    ents[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Ordena um array de objetos `Comparavel` usando o critério de comparação interno.
     *
     * @param comps o array a ser ordenado.
     */
    public static void ordenar(Comparavel[] comps) {
        for (int i = 0; i < comps.length - 1; i++) {
            for (int j = 0; j < comps.length - i - 1; j++) {
                if (comps[j].comparar(comps[j + 1]) > 0) {
                    Comparavel temp = comps[j];
                    comps[j] = comps[j + 1];
                    comps[j + 1] = temp;
                }
            }
        }
    }
}
