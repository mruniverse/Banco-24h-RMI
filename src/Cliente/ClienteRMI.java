/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Classes.Conta;
import Servico.Servico;
import com.google.gson.Gson;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class ClienteRMI {
    public static void main(String args[]) throws RemoteException{
        //int choice = 0;
        Registry reg = null;
        System.setProperty("java.rmi.server.hostname","172.16.200.102");


        System.out.println("Qual banco você deseja acessar?\n"
                + "[1] Banco de plástico\n"
                + "[2] Banco de ferro\n");
        
       Scanner scan = new Scanner(System.in);
       int choice = scan.nextInt();
       
       switch(choice){
           case 1:
               reg = LocateRegistry.getRegistry("localhost",9876);
               break;
           case 2:
               reg = LocateRegistry.getRegistry("172.16.200.74",9876);
               break;
           default:
               break;
       }
       
        try{
            Servico a = (Servico) reg.lookup("server");
            String json = a.ConsultarConta();
            Gson gson = new Gson();
            Conta Conta = gson.fromJson(json, Conta.class);
//            System.out.println(a.soma(10, 12));
            System.out.println(Conta.getNum_conta() + " " + Conta.getNome());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
