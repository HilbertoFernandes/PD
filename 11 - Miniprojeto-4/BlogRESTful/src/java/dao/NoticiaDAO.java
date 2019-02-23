package dao;

import java.util.ArrayList;
import java.util.List;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import entidade.Noticia;

public class NoticiaDAO {

    private ObjectContainer db = null;

    private void abrirBanco() {
        db = Db4oEmbedded.openFile("003");
    }

    private void fecharBanco() {
        db.close();
    }

    public void inserirNoticia(Noticia noticia) {
        abrirBanco();
        db.store(noticia);
        fecharBanco();
    }

    public List<Noticia> listarNoticias() {
        abrirBanco();
        ObjectSet lista_noticias = db.queryByExample(Noticia.class);
        List<Noticia> ln = new ArrayList<>();
        for (Object noticia : lista_noticias) {
            ln.add((Noticia) noticia);
        }
        fecharBanco();
        return ln;
    }

    public List<Noticia> listarNoticiasID(String id) {
        List<Noticia> noticias = listarNoticias();
        List<Noticia> noticias_encontradas = new ArrayList<>();
        for (Noticia y : noticias) {

            if (y.getId().equals(id)) {
                noticias_encontradas.add((Noticia) y);
                System.out.println("ENCONTROU");
            }
            fecharBanco();
        }
        return noticias_encontradas;
    }

    public void excluirNoticia(String id) {
        abrirBanco();
        Noticia noticia = new Noticia();
        Long.parseLong(id);
        ObjectSet resultado = db.queryByExample(noticia);
        Noticia deletada = (Noticia) resultado.next();
        db.delete(deletada);
        fecharBanco();
    }

    public Noticia buscarNoticiaTitulo(String titulo) {
        abrirBanco();
        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        ObjectSet resultado = db.queryByExample(noticia);
        Noticia localizada = (Noticia) resultado.next();
        fecharBanco();
        return localizada;
    }

    public void atualizarTitulo(String titulo) {
        Noticia noticia_anterior = buscarNoticiaTitulo(titulo);
        Noticia noticia_atualizada = null;
        noticia_atualizada.setTitulo(titulo);
        noticia_atualizada.setConteudo(noticia_anterior.getConteudo());
        noticia_atualizada.setAutor(noticia_anterior.getAutor());
        noticia_atualizada.setId(noticia_anterior.getId());
        db.store(noticia_atualizada);
    }

    public Noticia buscarNoticiaId(String id) {
        abrirBanco();
        Noticia noticia = new Noticia();
        noticia.setId(id);
        ObjectSet resultado = db.queryByExample(noticia);
        Noticia localizada = (Noticia) resultado.next();
        fecharBanco();
        return localizada;
    }

    public void atualizarNoticia(String id, String titulo) {
        abrirBanco();
        Noticia n = new Noticia();
        n.setId(id);
        ObjectSet resultado = db.queryByExample(n);
        Noticia n_resultado = (Noticia) resultado.next();
        n_resultado.setTitulo(titulo);
        db.store(n_resultado);
        fecharBanco();
    }
}
