package Servico;

import Classes.Conta;
import com.google.gson.Gson;
//import com.sun.xml.internal.txw2.output.XmlSerializer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServicoImp extends UnicastRemoteObject implements Servico {
    public ServicoImp() throws RemoteException{
        super();
    }

    @Override
    public String ConsultarConta() throws RemoteException {
        Conta a = new Conta();
        a.setAg(1515);
        a.setNome("Cleber");
        a.setNum_conta(4548);
        Gson gson = new Gson();
        String json = gson.toJson(a);
        return json;
    }


    @Override
    public long soma(long a, long b) throws RemoteException {
        return a + b; //To change body of generated methods, choose Tools | Templates.
    }
    
}
