package entidade;

import com.thoughtworks.xstream.XStream;
import dao.NoticiaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import java.util.Date;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("noticias")
public class NoticiaResource {

    private EntityManagerFactory emf;

    @Context
    private UriInfo context;

    public NoticiaResource() {
    }

    @POST
    @Path("inserir")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response inserirNoticia(@FormParam("titulo") String titulo,
            @FormParam("autor") String autor,
            @FormParam("data") String data,
            @FormParam("conteudo") String conteudo) {

        Response response;
        NoticiaDAO dao = new NoticiaDAO();
        List<Noticia> lista_de_noticias = dao.listarNoticias();
        List<String> lista_de_titulos = new ArrayList();

        for (Noticia n : lista_de_noticias) {
            lista_de_titulos.add(n.getTitulo());
        }

        if (lista_de_titulos.contains(titulo)) {
            response = Response
                    .status(Response.Status.CONFLICT)
                    .entity("<b><span style=\"color:red\">ERRO 409"
                            + "</span><b>A notícia já existe!")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else {

            Noticia noticia = new Noticia();
            noticia.setId(String.valueOf(new Date().getTime()));
            noticia.setTitulo(titulo);
            noticia.setAutor(autor);
            noticia.setData(data);
            noticia.setConteudo(conteudo);

            dao.inserirNoticia(noticia);

            response = Response
                    .status(Response.Status.OK)
                    .entity(noticia)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return response;
    }

    @GET
    @Path("listar")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response listarNoticias() {

        NoticiaDAO dao = new NoticiaDAO();
        String html = "<table class=\"table\">\n"
                + "  <thead>\n"
                + "    <tr>\n"
                + "      <th scope=\"col\">ID</th>\n"
                + "      <th scope=\"col\">TÌTULO</th>\n"
                + "      <th scope=\"col\">AUTOR</th>\n"
                + "    </tr>\n"
                + "  </thead>";

        List<Noticia> x = dao.listarNoticias();
        for (Noticia n : x) {
            html = html + "<tr><th scope='col'>" + n.getId() + "</th>"
                    + "<th scope='col'>" + n.getTitulo() + "</th>"
                    + "<th scope='col'>" + n.getAutor() + "</th></tr>";
        }

        html = html + " </tr>\n"
                + "  </tbody>\n"
                + "</table>";
        return Response.ok(html, MediaType.TEXT_HTML).build();
    }

    @PUT
    @Path("atualizar/{id}/{titulo}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response atualizarNoticia(@PathParam("id") String id,
            @PathParam("titulo") String titulo) {
        Response response;

        NoticiaDAO dao = new NoticiaDAO();
        List<Noticia> lista_de_noticias = dao.listarNoticias();
        List<String> lista_de_titulos = new ArrayList();
        List<String> lista_de_ids = new ArrayList();

        for (Noticia n : lista_de_noticias) {
            lista_de_titulos.add(n.getTitulo());
            lista_de_ids.add(n.getId());
        }

        if (lista_de_titulos.contains(titulo)) {
            response = Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("<b><span style=\"color:red\">ERRO 404"
                            + "</span><b> O Título informado já existe!")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else if (!lista_de_ids.contains(id)) {
            response = Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("<b><span style=\"color:red\">ERRO 404"
                            + "</span><b> O ID informado não foi encontrado.")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else {
            dao.atualizarNoticia(id, titulo);

            return Response
                    .status(Response.Status.OK)
                    .entity(dao.buscarNoticiaId(id))
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        }
        return response;
    }

    @DELETE
    @Path("remover/{id}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response removerNoticia(@PathParam("id") String id) {
        Response response;
        NoticiaDAO dao = new NoticiaDAO();

        List<Noticia> lista_de_noticias = dao.listarNoticias();
        List<String> lista_de_ids = new ArrayList();

        for (Noticia n : lista_de_noticias) {
            lista_de_ids.add(n.getId());
        }

        if (!lista_de_ids.contains(id)) {
            response = Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("<b><span style=\"color:red\">ERRO 404"
                            + "</span><b> O ID informado não foi encontrado.")
                    .type(MediaType.APPLICATION_XML)
                    .build();
        } else {

            XStream xstream = new XStream();
            String excluida = xstream.toXML(dao.buscarNoticiaId(id));
            Noticia n = dao.buscarNoticiaId(id);
            dao.excluirNoticia(n.getId());

            response = Response
                    .status(Response.Status.OK)
                    .entity(excluida)
                    .type(MediaType.APPLICATION_XML)
                    .build();
        }
        return response;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response recuperarNoticia(@QueryParam("id") String id) {
        Response response;
        NoticiaDAO dao = new NoticiaDAO();
        List<Noticia> localizada = dao.listarNoticiasID(id);

        System.out.println("--------------->"+localizada.size());
        
        if (localizada.isEmpty()) {
            System.out.println("------entrou mo 404-------------------->"+id);
            String html_html = "<b><span style=\"color:red\">ERRO 404"
                    + "</span><b> O ID informado não foi encontrado.";
            response = Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(html_html)
                    .type(MediaType.TEXT_HTML)
                    .build();
        } else {
            Noticia noticia = dao.buscarNoticiaId(id);
            String html_html = "<div class=\"col-sm\"\n"
                    + "style=\"box-shadow: 0px -1px 35px 2px rgba(0, 0, 0, 0.57);"
                    + " margin-top: 1%;  width: 25%; padding: 2%;\"><h3><b>ID :</b>" + noticia.getId()
                    + "<br><b>Título</b>" + noticia.getTitulo()
                    + "<br><b>Data</b>" + noticia.getData()
                    + "<br><b>Conteúdo :</b>" + noticia.getConteudo() + "<h3></div>";

            response = Response
                    .status(Response.Status.OK)
                    .entity(html_html)
                    .type(MediaType.TEXT_HTML)
                    .build();
        }
        System.out.println("-------------------------->"+id);
        return response;
    }

}
