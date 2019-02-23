package br.edu.ifpb.miniprojeto3.servico;

import br.edu.ifpb.pb.miniprojeto3.dao.FilmeDAO;
import br.edu.ifpb.pd.miniprojeto3.entidade.Filme;
import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(serviceName = "FilmeService")
public class FilmeService {

    @WebMethod(operationName = "salvarFilme")
    @WebResult(name = "resposta")
    public String adicionarFilme(@WebParam(name = "titulo") String titulo, @WebParam(name = "diretor") String diretor,
            @WebParam(name = "estudio") String estudio, @WebParam(name = "genero") String genero,
            @WebParam(name = "ano") String ano) {

        Filme filme = new Filme();
        filme.setId(String.valueOf(new Date().getTime()));
        filme.setAno(ano);
        filme.setTitulo(titulo);
        filme.setEstudio(estudio);
        filme.setDiretor(diretor);
        filme.setGenero(genero);

        FilmeDAO db = new FilmeDAO();
        db.inserirFilme(filme);

        return "OK! - Filme salvo";
    }

    @WebMethod(operationName = "atualizarFilme")
    @WebResult(name = "resposta")
    public String atualizarFilme(@WebParam(name = "id") String id, @WebParam(name = "titulo") String titulo, @WebParam(name = "diretor") String diretor,
            @WebParam(name = "estudio") String estudio, @WebParam(name = "genero") String genero,
            @WebParam(name = "ano") String ano) {

        System.out.println("entrou em atualizar");
        Filme filme = new Filme();
        filme.setId(id);
        filme.setAno(ano);
        filme.setTitulo(titulo);
        filme.setEstudio(estudio);
        filme.setDiretor(diretor);
        filme.setGenero(genero);

        FilmeDAO db = new FilmeDAO();
        db.atualizarFilme(filme);

        return "Filme atualizado.";
    }

    @WebMethod(operationName = "excluirFilme")
    @WebResult(name = "resposta")
    public String excluirFilme(@WebParam(name = "id") String id) {
        FilmeDAO db = new FilmeDAO();
        db.excluirFilme(id);
        return "Filme Excluido.";
    }

    @WebMethod(operationName = "buscaFilmeId")
    @WebResult(name = "resposta")
    public Filme buscaFilmeId(@WebParam(name = "id") String id) {
        FilmeDAO db = new FilmeDAO();
        Filme localizado = db.buscarFilmeId(id);
        return localizado;
    }

    @WebMethod(operationName = "listarFilmes")
    @WebResult(name = "resposta")
    public List<Filme> listarFilmes() {
        FilmeDAO db = new FilmeDAO();
        List<Filme> filmes = db.listarFilmes();
        return filmes;
    }

    @WebMethod(operationName = "qtdFilmes")
    @WebResult(name = "resposta")
    public int qtdFilmes() {
        FilmeDAO db = new FilmeDAO();
        return db.qtdFilmes();
    }
}
