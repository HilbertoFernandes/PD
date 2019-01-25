/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pd;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Professor
 */
public interface ContadorIF extends Remote{
    public void inc() throws RemoteException;
    public int get() throws RemoteException;    
}
