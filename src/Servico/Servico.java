
package Servico;

import Servidor.Conta;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Servico extends Remote {
    
   public String RegistrarConta(int ag, int conta, String nome, String cpf) throws RemoteException;
   public String ConsultarCadastroPorCpf(String cpf) throws RemoteException;
   public String AlterarCadastro(int ag, int conta, String nome, String cpf) throws RemoteException;
   public void RealizarDepósito(int ag, int conta, double saldo) throws RemoteException;
   public void RealizarSaque(int ag, int conta, double valor) throws RemoteException;
}
