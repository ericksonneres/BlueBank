
package modelo;

import java.io.Serializable;

/**
 *
 * @author Erickson
 */
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String cpf;
    private String numeroAgencia;
    private String numeroConta;
    private float saldo;

    //Construtores
    public Conta(){}
    public Conta(Long id,String cpf, String numeroAgencia, String numeroConta){
        this.id = id;
        this.cpf = cpf;
        this.numeroAgencia = numeroAgencia;
        this.numeroConta = numeroConta;
    }
    public Conta(Long id,String cpf, String numeroAgencia, String numeroConta, float saldo){
        this.id = id;
        this.cpf = cpf;
        this.numeroAgencia = numeroAgencia;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }
    
    //metodos de acesso
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(String numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }
    
    @Override
    public Conta clone() {
        return new Conta(id, cpf, numeroAgencia, numeroConta);
    }
    
    public void restore(Conta conta) {
        this.id = conta.getId();
        this.cpf = conta.getCpf();
        this.numeroAgencia = conta.getNumeroAgencia();
        this.numeroConta = conta.getNumeroConta();
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
