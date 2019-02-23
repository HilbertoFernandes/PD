/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.tsi.pd.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * REST Web Service
 *
 * @author 2420625
 */
@XmlRootElement
public class Aluno {

    private String id;
    private String nome;

    /**
     * Creates a new instance of Aluno
     */
    public Aluno(String id, String nome) {
        this.id = id;
        this.nome= nome;
    }
    public Aluno() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
