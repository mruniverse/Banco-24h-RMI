package Servico;

import Servidor.Conta;
import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetImpl;
//import com.sun.xml.internal.txw2.output.XmlSerializer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ServicoImp extends UnicastRemoteObject implements Servico {
    private Connection conn;
    
    public ServicoImp(Connector db, Connection conn) throws RemoteException{
        super();
        this.conn = conn;
    }
//==============================================================================
    @Override
    public String RegistrarConta(int ag, int conta, String nome, String cpf) throws RemoteException {
        Conta c = new Conta(ag, conta, nome, cpf);
        
        try {
            Statement stmt = (Statement) this.conn.createStatement();
//            Query ============================================================
            String query = 
                    "INSERT INTO conta (ag, num_conta, nome, cpf, saldo)"
                    + " VALUES "
                    + "('"+c.getAg()+"','"+c.getNum_conta()+"',"
                    + "'"+c.getNome()+"','"+c.getCpf()+"','"+c.getSaldo()+"')";
//            Query ============================================================
            stmt.executeUpdate(query);
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        Gson gson = new Gson();
        String json = gson.toJson(c);
        return json;
    }
//==============================================================================
    @Override
    public String ConsultarCadastroPorCpf(String cpf) throws RemoteException {
        Conta c = new Conta(0, 0, "", "");
        
        try {
            Statement stmt = (Statement) this.conn.createStatement();
//            Query ============================================================
            String query = "SELECT * FROM conta WHERE cpf = '"+cpf+"'";
//            Query ============================================================
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                c.setNome(rs.getString("nome"));
//                Date dateCreated = rs.getDate("date_created");
                c.setAg(rs.getInt("ag"));
                c.setNum_conta(rs.getInt("num_conta"));
                c.setSaldo(rs.getDouble("saldo"));
            }
                
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        Gson gson = new Gson();
        String json = gson.toJson(c);
        return json;
    }
//==============================================================================
    @Override
    public String AlterarCadastro(int ag, int conta, String nome, String cpf) throws RemoteException {
        Conta c = new Conta(ag, conta, nome, cpf);
        
        try {
            Statement stmt = (Statement) this.conn.createStatement();
//            Query ============================================================
            String query = 
                    "UPDATE conta "
                    + "SET "
                    + "ag = '"+ag+"',"
                    + "num_conta = '"+conta+"',"
                    + "nome = '"+nome+"' "
//                    + "cpf = '"+cpf+"'"
                    + "WHERE cpf = '"+cpf+"'";
//            Query ============================================================
            stmt.executeUpdate(query);
            
        } catch (SQLException e) {
            System.out.println("CPF inválido!");
            System.err.println(e);
        }
        
        Gson gson = new Gson();
        String json = gson.toJson(c);
        return json;
    }
//==============================================================================
    @Override
    public void RealizarDepósito(int ag, int conta, double saldo) throws RemoteException {
        double valor = 0;
        
        try {
            Statement stmt = (Statement) this.conn.createStatement();
//            Query ============================================================
            String query = "SELECT * FROM conta WHERE ag = '"+ag+"' AND num_conta = '"+conta+"'";
//            Query ============================================================
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                valor = rs.getDouble("saldo");
            }
            saldo += valor;
            
//            Query2 ============================================================
            String query2 = 
                    "UPDATE conta "
                    + "SET saldo = '"+saldo+"' "
                    + "WHERE ag = '"+ag+"' "
                    + "AND num_conta = '"+conta+"'";
//            Query2 ============================================================
            stmt.executeUpdate(query2);
            
        } catch (SQLException e) {
            System.out.println("Conta inválida!");
            System.err.println(e);
        }        
    }
//==============================================================================

    @Override
    public void RealizarSaque(int ag, int conta, double valor) throws RemoteException {
        double saldo = 0;
        try {
            Statement stmt = (Statement) this.conn.createStatement();
//            Query ============================================================
            String query = "SELECT * FROM conta WHERE ag = '"+ag+"' AND num_conta = '"+conta+"'";
//            Query ============================================================
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                saldo = rs.getDouble("saldo");
            }
            saldo -= valor;
            
//            Query2 ============================================================
            String query2 = 
                    "UPDATE conta "
                    + "SET saldo = '"+saldo+"' "
                    + "WHERE ag = '"+ag+"' "
                    + "AND num_conta = '"+conta+"'";
//            Query ============================================================
            stmt.executeUpdate(query2);

                
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    
//    @Override
//    public long soma(long a, long b) throws RemoteException {
//        return a + b; //To change body of generated methods, choose Tools | Templates.
//    }
    
}
