package br.gov.cesarschool.poo.daogenerico;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DAOSerializadorObjetos {
    private final String nomeDiretorio;

    public DAOSerializadorObjetos(Class<?> tipoEntidade) {
        this.nomeDiretorio = "." + File.separator + tipoEntidade.getSimpleName();
        File dir = new File(nomeDiretorio);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public boolean incluir(Entidade entidade) {
        File arquivo = new File(nomeDiretorio + File.separator + entidade.getIdUnico());
        if (arquivo.exists()) {
            return false;
        }
        entidade.setDataHoraInclusao(LocalDateTime.now());
        return gravarArquivo(arquivo, entidade);
    }

    public boolean alterar(Entidade entidade) {
        File arquivo = new File(nomeDiretorio + File.separator + entidade.getIdUnico());
        if (!arquivo.exists()) {
            return false;
        }
        entidade.setDataHoraUltimaAlteracao(LocalDateTime.now());
        return gravarArquivo(arquivo, entidade);
    }

    public boolean excluir(String idUnico) {
        File arquivo = new File(nomeDiretorio + File.separator + idUnico);
        return arquivo.exists() && arquivo.delete();
    }

    public Entidade buscar(String idUnico) {
        File arquivo = new File(nomeDiretorio + File.separator + idUnico);
        if (!arquivo.exists()) {
            return null;
        }
        return lerArquivo(arquivo);
    }

    public Entidade[] buscarTodos() {
        File dir = new File(nomeDiretorio);
        File[] arquivos = dir.listFiles();
        if (arquivos == null || arquivos.length == 0) {
            return new Entidade[0];
        }
        List<Entidade> entidades = new ArrayList<>();
        for (File arquivo : arquivos) {
            Entidade entidade = lerArquivo(arquivo);
            if (entidade != null) {
                entidades.add(entidade);
            }
        }
        return entidades.toArray(new Entidade[0]);
    }

    private boolean gravarArquivo(File arquivo, Entidade entidade) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(entidade);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Entidade lerArquivo(File arquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (Entidade) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
