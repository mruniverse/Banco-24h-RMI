/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Servico.Connector;
import Servico.ServicoImp;
import com.mysql.jdbc.Connection;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ServidorRMI {

    public static void main(String args[]){
        Connector db = new Connector();
        Connection conn = (Connection) db.connect();

        try {
            ServicoImp a = new ServicoImp(db, conn);
            Registry reg = LocateRegistry.createRegistry(9876);
            reg.rebind("Server", a);
            System.out.println("Servidor do banco iniciado!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    

}
