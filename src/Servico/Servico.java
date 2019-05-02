
package Servico;

import Classes.Conta;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Servico extends Remote {
    
   public String ConsultarConta() throws RemoteException;
   
   public long soma(long a, long b) throws RemoteException;
}
