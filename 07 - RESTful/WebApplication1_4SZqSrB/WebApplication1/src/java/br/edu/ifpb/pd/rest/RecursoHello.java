/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pd.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author 2420625
 */
@Path("hello")
public class RecursoHello {

    @Context
    private UriInfo context;

    public RecursoHello() {}
     
    @GET
    public String getText() {
        return "Eu sou um serviço REST";
    }
    
    @GET
    @Path("{nome}")
    public String getText2(@PathParam("nome")
            String nome) {
        return "Ola, "+nome;
    }
    @GET    
    @Path("nome")
    public Response getText3() {
        String html ="<html><body>Teste</body></html>";
        return Response.ok(html, 
                MediaType.TEXT_HTML).build();
    }
}
