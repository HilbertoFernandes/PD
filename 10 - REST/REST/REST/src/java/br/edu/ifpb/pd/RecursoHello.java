/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pd;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author 20142370287
 */
@Path("hello")
public class RecursoHello {

    @Context
    private UriInfo context;

   
    public RecursoHello() {
    }

 
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String geText() {
        return "Serviço respondendo";
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{nome}")
    public String getText2(@PathParam("nome") String nome) {
        return "Olá, " + nome;
    }
    
    @GET
    @Path("{html}")
    public Response getText2() {
        String html = "<html><body><h1>Teste</h1></body></html>";
        return Response.ok(html, MediaType.TEXT_HTML).build();
    }
    
   

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/subtrair/{n1}/{n2}")
    public String getSubtracao(@PathParam("n1") String n1, @PathParam("n2") String n2) {
        int numero_a = Integer.parseInt(n1);
        int numero_b = Integer.parseInt(n2);
        String resultado =  String.valueOf(numero_a - numero_b);
        return resultado;
    }
    
     @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("soma/{n1}/{n2}")
    public int getSoma(@PathParam("n1") int n1, @PathParam("n2") int n2) {
                return n1-n2;
    }
}
