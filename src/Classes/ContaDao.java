
package Classes;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import util.JpaUtil;


public class ContaDao {
    public boolean inserir(Conta conta){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(conta);
        tx.commit();
        manager.close();
        return true;
    }
    
     public List<Conta> listarTodos(){
        EntityManager manager = JpaUtil.getEntityManager();
        CriteriaQuery<Conta> query = manager.getCriteriaBuilder().createQuery(Conta.class);
        query.select(query.from(Conta.class));
        List<Conta> lista = manager.createQuery(query).getResultList();
        manager.close();
        
        return lista;
    }
     
      public boolean excluir(Conta conta){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction(); 
        tx.begin();
        // recupera a referência ao objeto
        Conta temp = manager.find(Conta.class, conta.getId());
        manager.remove(temp);
        tx.commit();
        manager.close();
        
        return true;
    }
      
    public Conta buscarPorConta(String num_conta){
        EntityManager manager = JpaUtil.getEntityManager();
        Query query = manager.createQuery("SELECT c FROM Conta as c WHERE c.num_conta =:num_conta");
        query.setParameter("num_conta", num_conta);
        Conta conta = (Conta) query.getResultList().get(0);
        manager.close();
        
        return conta;
    }
    
     public Conta buscarPorNome(String nome){
        EntityManager manager = JpaUtil.getEntityManager();
        Query query = manager.createQuery("SELECT c FROM Conta as c WHERE c.nome =:nome");
        query.setParameter("nome", nome);
        Conta conta = (Conta) query.getResultList().get(0);
        manager.close();
        
        return conta;
    }
      
    public boolean contaJaCadastrada(int num_conta){
        EntityManager manager = JpaUtil.getEntityManager();
        Conta conta = null;
        Query query = manager.createQuery("SELECT c FROM Conta as c WHERE c.num_conta =:num_conta");
        query.setParameter("num_conta", num_conta);
        try{
            conta = (Conta) query.getSingleResult();
        }
        catch(Exception e){
        }
        manager.close();
        
        if(conta == null){
            return false;   
        }
        else{
            return true;
        }
    }
    
      public boolean alterar(Conta conta){
        EntityManager manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(conta);
        manager.getTransaction().commit();
        manager.close();
        return true;
    }
}