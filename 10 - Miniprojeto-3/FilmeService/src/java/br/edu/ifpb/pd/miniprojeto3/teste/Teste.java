package br.edu.ifpb.pd.miniprojeto3.teste;

import br.edu.ifpb.pb.miniprojeto3.dao.FilmeDAO;

import br.edu.ifpb.pd.miniprojeto3.entidade.Filme;
import java.util.List;

public class Teste {

    public static void main(String[] args) {

        FilmeDAO db = new FilmeDAO();

        List<Filme> filmes = db.listarFilmes();
        for (Filme f : filmes) {
            System.out.println(f.toString());
        }
    }
}
