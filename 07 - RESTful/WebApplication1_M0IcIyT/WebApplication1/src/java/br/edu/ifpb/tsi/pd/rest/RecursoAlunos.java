/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.tsi.pd.rest;

import java.util.HashMap;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author 2420625
 */
@Path("/alunos")
public class RecursoAlunos {

    @Context
    private UriInfo context;
    private HashMap<String,Aluno> alunos = new HashMap<String,Aluno>();
    
    public RecursoAlunos() {
        alunos.put("1",new Aluno("1","Bia"));
        alunos.put("2",new Aluno("2","Teteia"));
        alunos.put("3",new Aluno("3","Bruno"));
    }

    /**
     * Sub-resource locator method for {id}
     */
    @GET
    @Path("{id}")
    public Response getAluno(@PathParam("id") String id,
            @QueryParam("formato")String formato) {
        if (alunos.containsKey(id)){
            if (formato.equals("xml"))
                return Response.ok(
                    alunos.get(id),
                        MediaType.APPLICATION_XML).build();
            else if (formato.equals("html")){
                String html = "<html><body>"+
                        alunos.get(id).getNome()
                        +"</body></html>";
                return Response.ok(
                    html,
                        MediaType.TEXT_HTML).build();
            }
            else return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    public String teste() {
        return "Teste";
    }
    
    @POST
    public Response cadastrar(@FormParam("matricula")String matricula, 
            @FormParam("nome")String nome){
        alunos.put(matricula,new Aluno(matricula,nome));
        System.out.println("O Aluno xegou");
        //OU
        //return Response.status(Response.Status.CREATED).build();
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{id}")
    public Response remover(@PathParam("id")String matricula){
        if (!alunos.containsKey(matricula))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(alunos.remove(matricula),
                MediaType.APPLICATION_XML).build();
    }
}
