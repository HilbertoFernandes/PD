/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pd;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Professor
 */
public class ContadorRemoto extends UnicastRemoteObject
        implements ContadorIF{
    private int cont;

    public ContadorRemoto() throws RemoteException{
        cont = 0;
    }
    @Override
    public void inc() throws RemoteException {
        cont++;
    }

    @Override
    public int get() throws RemoteException {
        return cont;
    }
    
    public static void main(String x[]) throws MalformedURLException{
        try {
            ContadorIF contador = new ContadorRemoto();
            LocateRegistry.createRegistry(1099);
            Naming.bind("contador", contador);
        } catch (RemoteException ex) {
            Logger.getLogger(ContadorRemoto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(ContadorRemoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
