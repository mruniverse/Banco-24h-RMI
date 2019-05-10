/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Servidor.Conta;
import Servico.Servico;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.rmi.NotBoundException;
//import com.mysql.jdbc.Connection;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class ClienteRMI {
    public static void main(String args[]) throws RemoteException{
        //int choice = 0;
        String json = "";
        Registry reg = null;
        System.setProperty("java.rmi.server.hostname","127.0.0.1");


        System.out.println("Qual banco você deseja acessar?\n"
                + "[1] Banco de plástico\n"
                + "[2] Banco de ferro\n");
        
       Scanner scan = new Scanner(System.in);
       int choice = scan.nextInt();
       
       switch(choice){
           case 1:
               reg = LocateRegistry.getRegistry("127.0.0.1",9876);
               break;
           case 2:
               reg = LocateRegistry.getRegistry("127.0.0.1",9876);
               break;
           default:
               break;
       }
       
        try{
            Servico a = (Servico) reg.lookup("Server");
            
//            json = a.RegistrarConta(3123, 123131, "Yuri Lucas Luz da Silva", "47938751812");    
//            json = a.ConsultarCadastroPorCpf("47938751812");
            json = a.AlterarCadastro(3123, 123131, "Yuri L L da Silva", "47938751812");    
//            a.RealizarDepósito(3123, 123131, 100);   
//            a.RealizarSaque(3123, 123131, 50);

            json = a.ConsultarCadastroPorCpf("47938751812");

            
            
//            Transform json to object =========================================
            Gson gson = new Gson();
            Conta Conta = gson.fromJson(json, Conta.class);
//            Transform json to object =========================================
            System.out.println(Conta.getNum_conta() + " " + Conta.getNome() + " " + Conta.getSaldo());
            
        }catch (JsonSyntaxException | NotBoundException | RemoteException e){
            e.printStackTrace();
        }
    }

}
