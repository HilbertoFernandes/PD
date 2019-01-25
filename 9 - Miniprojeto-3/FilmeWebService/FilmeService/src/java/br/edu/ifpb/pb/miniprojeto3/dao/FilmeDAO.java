<<<<<<< HEAD
package br.edu.ifpb.pb.miniprojeto3.dao;

import java.util.ArrayList;
import java.util.List;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import br.edu.ifpb.pd.miniprojeto3.entidade.Filme;

public class FilmeDAO {

    private ObjectContainer db = null;

    private void abrirBanco() {
        db = Db4oEmbedded.openFile("banco");
    }

    private void fecharBanco() {
        db.close();
    }

    public void inserirFilme(Filme f) {
        abrirBanco();
        db.store(f);
        fecharBanco();
    }

    public List<Filme> listarFilmes() {
        abrirBanco();
        ObjectSet listaFilmes = db.queryByExample(Filme.class);
        List<Filme> lf = new ArrayList<>();
        for (Object filme : listaFilmes) {
            lf.add((Filme) filme);
        }
        fecharBanco();
        return lf;
    }
    
     public int qtdFilmes() {
        abrirBanco();
        int quantidade = db.queryByExample(Filme.class).size();
        fecharBanco();
        return quantidade;
    }

    public Filme buscarFilme(Filme f) {
        abrirBanco();
        ObjectSet resultado = db.queryByExample(f);
        Filme filme = (Filme) resultado.next();
        fecharBanco();
        return filme;
    }

    public Filme buscarFilmeId(String id) {
        abrirBanco();
        Filme filme = new Filme();
        filme.setId(id);
        ObjectSet resultado = db.queryByExample(filme);
        Filme localizado = (Filme) resultado.next();
        fecharBanco();
        return localizado;
    }

    public void excluirFilme(String id) {
        abrirBanco();
        Filme filme = new Filme();
        Long.parseLong(id);
        ObjectSet resultado = db.queryByExample(filme);
        Filme deletado = (Filme) resultado.next();
        db.delete(deletado);
        fecharBanco();
    }
    
     public Filme buscarFilmeTitulo(String titulo) {
        abrirBanco();
        Filme filme = new Filme();
        filme.setTitulo(titulo);
        ObjectSet resultado = db.queryByExample(filme);
        Filme localizado = (Filme) resultado.next();
        fecharBanco();
        return localizado;
    }
    

    public void atualizarFilme(Filme filme) {
        abrirBanco();
        Filme procurado = new Filme();
        procurado.setId(filme.getId());
        ObjectSet resultado = db.queryByExample(procurado);
        Filme localizado = (Filme) resultado.next();
        localizado.setAno(filme.getAno());
        localizado.setDiretor(filme.getDiretor());
        localizado.setEstudio(filme.getEstudio());
        localizado.setGenero(filme.getGenero());
        localizado.setTitulo(filme.getTitulo());
        db.store(localizado);
        fecharBanco();
    }
}
=======
package br.edu.ifpb.pb.miniprojeto3.dao;

import java.util.ArrayList;
import java.util.List;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import br.edu.ifpb.pd.miniprojeto3.entidade.Filme;

public class FilmeDAO {

    private ObjectContainer db = null;

    private void abrirBanco() {
        db = Db4oEmbedded.openFile("banco");
    }

    private void fecharBanco() {
        db.close();
    }

    public void inserirFilme(Filme f) {
        abrirBanco();
        db.store(f);
        fecharBanco();
    }

    public List<Filme> listarFilmes() {
        abrirBanco();
        ObjectSet listaFilmes = db.queryByExample(Filme.class);
        List<Filme> lf = new ArrayList<>();
        for (Object filme : listaFilmes) {
            lf.add((Filme) filme);
        }
        fecharBanco();
        return lf;
    }
    
     public int qtdFilmes() {
        abrirBanco();
        int quantidade = db.queryByExample(Filme.class).size();
        fecharBanco();
        return quantidade;
    }

    public Filme buscarFilme(Filme f) {
        abrirBanco();
        ObjectSet resultado = db.queryByExample(f);
        Filme filme = (Filme) resultado.next();
        fecharBanco();
        return filme;
    }

    public Filme buscarFilmeId(String id) {
        abrirBanco();
        Filme filme = new Filme();
        filme.setId(id);
        ObjectSet resultado = db.queryByExample(filme);
        Filme localizado = (Filme) resultado.next();
        fecharBanco();
        return localizado;
    }

    public void excluirFilme(String id) {
        abrirBanco();
        Filme filme = new Filme();
        Long.parseLong(id);
        ObjectSet resultado = db.queryByExample(filme);
        Filme deletado = (Filme) resultado.next();
        db.delete(deletado);
        fecharBanco();
    }
    
     public Filme buscarFilmeTitulo(String titulo) {
        abrirBanco();
        Filme filme = new Filme();
        filme.setTitulo(titulo);
        ObjectSet resultado = db.queryByExample(filme);
        Filme localizado = (Filme) resultado.next();
        fecharBanco();
        return localizado;
    }
    

    public void atualizarFilme(Filme filme) {
        abrirBanco();
        Filme procurado = new Filme();
        procurado.setId(filme.getId());
        ObjectSet resultado = db.queryByExample(procurado);
        Filme localizado = (Filme) resultado.next();
        localizado.setAno(filme.getAno());
        localizado.setDiretor(filme.getDiretor());
        localizado.setEstudio(filme.getEstudio());
        localizado.setGenero(filme.getGenero());
        localizado.setTitulo(filme.getTitulo());
        db.store(localizado);
        fecharBanco();
    }
}
>>>>>>> ab6ef55d7acc2386a179fe45360418886a0a66a8
