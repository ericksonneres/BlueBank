/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import modelo.Conta;
import util.DataConnect;



/**
 *
 * @author erid
 */

@ManagedBean(name = "TableDAO")
@ApplicationScoped
public class TableDAO implements Serializable{

    
    public ArrayList<Conta> getListagemContas() {
        ArrayList<Conta> lista = new ArrayList<Conta>();
        String resp = "";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM conta ORDER BY id;";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                Conta cidade = new Conta();
                
                cidade.setId(rs.getLong("id"));
                cidade.setCpf(rs.getString("cpf"));
                cidade.setNumeroAgencia(rs.getString("numeroAgencia"));
                cidade.setNumeroConta(rs.getString("numeroConta"));
                cidade.setSaldo(rs.getFloat("saldo"));
                
                
                lista.add(cidade);
            }
            rs.close();
            stmt.close();
            //con.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            resp = ex.toString();
        } finally {
            DataConnect.close(con);
        }
        return lista;
    }
    
    public String inserirConta(Conta conta){
        String resp = "";
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            con = DataConnect.getConnection();
            String sql = "INSERT INTO conta (cpf,numeroAgencia,numeroConta,saldo) "
                    + "VALUES(?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,conta.getCpf());
            ps.setString(2,conta.getNumeroAgencia());
            ps.setString(3,conta.getNumeroConta());
            ps.setFloat(4,conta.getSaldo());
            
            ps.execute();
            ps.close();
            con.close();
            
            resp="incluido com sucesso!";
            
        }
        catch(Exception er){
            resp = er.toString();
        }
        
        return resp;
        
    }
    
    public String alteraSaldo( String numeroContaOrigem, String numeroContaDestino, float valor){
        
        String resp = "";
        String sql = "";
        Connection con = null;
        PreparedStatement ps = null;
        try{
        
            con = DataConnect.getConnection();
            //sql = "UPDATE conta SET saldo = saldo + " + valor + "" 
            //        + "WHERE numeroConta= '" + numeroContaDestino +"'";
            
            sql = "UPDATE conta SET saldo = CASE " +
                    " WHEN numeroConta = '" + numeroContaOrigem + "' THEN  " + " saldo - " + valor +
                    " WHEN numeroConta = '" + numeroContaDestino + "' THEN  " +  " saldo + " + valor +
                    " END " +
                     "WHERE numeroConta IN ('" + numeroContaDestino +"', '" + numeroContaOrigem + "')";
            
            ps = con.prepareStatement(sql);
                        
            ps.execute();
            
            resp = "Dados Alterados com sucesso!";
            
            con.close();
            ps.close();
            
        }catch(Exception ex){
            resp = ex.toString();
        }
        
        return resp;
    }
    
    public Conta pegarConta(String numeroConta) {
        String resp = "";
        Conta conta1 = new Conta();
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();

            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM conta WHERE numeroConta ='" + numeroConta + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {

                conta1.setId(rs.getLong("id"));
                conta1.setCpf(rs.getString("cpf"));
                conta1.setNumeroAgencia(rs.getString("numeroAgencia"));
                conta1.setNumeroConta(rs.getString("numeroConta"));
                conta1.setSaldo(rs.getFloat("saldo"));

            } else {
                conta1 = null;
            }
            rs.close();
            stmt.close();
            //con.close();

        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            resp = ex.toString();
        } catch (Exception e) {
            System.out.println("Error -->" + e.getMessage());
        } finally {
            DataConnect.close(con);

        }
        return conta1;

    }
}
