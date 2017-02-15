
package managedbean;

import dao.TableDAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
    
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Conta;

/**
 *
 * @author Izelk
 */
@ManagedBean
public class ContaMB implements Serializable {
    private static final long serialVersionUID = 1L;
    
   @ManagedProperty("#{TableDAO}")
    private TableDAO service;
    
    private List<Conta> list = null;
    private Conta item = new Conta();
    private Conta item2 = new Conta();
    
    
    @PostConstruct
    public void init() {
        list = service.getListagemContas();

    }
    
    public void setService(TableDAO service) {
        this.service = service;
    }
    
    //****************************************************
    public List<Conta> getContas() {
        return list;
    }
    
    
    public void transfereValor() {
        Conta contaOrig = service.pegarConta(item.getNumeroConta());
        Conta contaDes = service.pegarConta(item2.getNumeroConta());
        String resp = getErrorText(contaOrig, contaDes);
        if (resp.equalsIgnoreCase("") ) {
            resp = service.alteraSaldo(contaOrig.getNumeroConta(), contaDes.getNumeroConta(), item.getSaldo() );
        }
        if (resp.equals("Dados Alterados com sucesso!") ) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Transferido com sucesso"));
           
        } else {
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Erro ao Transferir: " + resp));
                    
        }
    }
    
    public String getErrorText(Conta conta,Conta contaDes){
        String resp = "";
        
        List<Conta> listlist = Arrays.asList(conta, contaDes);
                
        for (Conta listlist1 : listlist) {
            if (conta == null ) {
                resp = "Conta: " + item.getNumeroConta() + " não existe!";
                break;
            } else if (contaDes == null) {
                resp = "Conta: " + item2.getNumeroConta() + " não existe!";
                break;
            } else if (!conta.getNumeroAgencia().equalsIgnoreCase(item.getNumeroAgencia())) {
                resp = "Número Agencia: " + item.getNumeroAgencia() + " incorreto!";
                break;
            } else if (!listlist1.getNumeroConta().equalsIgnoreCase(item.getNumeroConta()) &&
                    !listlist1.getNumeroConta().equalsIgnoreCase(item2.getNumeroConta())) {
                resp = "Número Conta de Origem e/ou Destino incorreto!";
                break;
            } else if (conta.getSaldo() <= 0 || item.getSaldo() > conta.getSaldo()) {
                resp = "Saldo insuficiente!";
                break;
            }
        }

        return resp;
    }
    
    public void addConta() {
        // DAO salvar conta
        String resp = "";
        resp = new TableDAO().inserirConta(item );
        if (resp.equals("incluido com sucesso!") ) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Transferido com sucesso"));
           
        } else {
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Erro ao Transferir: " + resp));
                    
        }
    }
       
    
    public List<Conta> getList() {
        return list;
    }
    
    public Conta getItem() {
        return this.item;
    }
    
    public Conta getItem2() {
        return this.item2;
    }
    
   
    
}
