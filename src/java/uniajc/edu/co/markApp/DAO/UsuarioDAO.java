/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniajc.edu.co.markApp.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import markApp.prueba.ConexionDB;
import uniajc.edu.co.markApp.VO.Usuario;

/**
 *
 * @author PC
 */
public class UsuarioDAO {

    private Usuario usr;
    ConexionDB baseDatos;
    Connection con;
    Statement st;
    ResultSet rs;
    String sql;

    public UsuarioDAO() {
        baseDatos = new ConexionDB();
        con = baseDatos.generaConexion("jdbc:oracle:thin:MARKAPP/MARKAPP@LOCALHOST:1521:xe");
        System.out.println("Cargado con exito ...");
    }

    public int consultarUsuario() {
        ArrayList lista;
        int numero = -1;
        String dato[];
        sql = "SELECT COD_EMPLEADO FROM USUARIOS";
        System.out.print(sql);
        lista = ConexionDB.datosBusqueda(sql, 1);
        System.out.print(" Cantidad"+lista.size());
        if ((lista != null) && (lista.size() > 0)) {
            dato = (String[]) lista.get(0);
            numero = Integer.parseInt(dato[0]);
        }else{
            System.out.print("Entro al else");
        }
        System.out.print("El numero consultado es "+numero);
        return numero;
    }
    
    public int validarUsuario(int numero){
        int estado;
        ArrayList lista;        
        String dato[];
        sql = "SELECT COD_EMPLEADO FROM USUARIOS WHERE COD_EMPLEADO ="+numero;
        System.out.print(sql);
        lista = ConexionDB.datosBusqueda(sql, 1);
        System.out.print(" Cantidad "+lista.size());
        if ((lista != null) && (lista.size() > 0)) {
            dato = (String[]) lista.get(0);
            numero = Integer.parseInt(dato[0]);
            estado = lista.size();
        }else{
            estado = 0;
        }        
        return estado;
    }
}
