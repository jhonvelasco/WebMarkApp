/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markApp.prueba;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import uniajc.edu.co.markApp.DAO.UsuarioDAO;

/**
 *
 * @author PC
 */
@WebService(serviceName = "MyWebServices")
public class MyWebServices {

    UsuarioDAO usr;
    int resultado;
    boolean estado; 
    
    public MyWebServices(){
        usr = new UsuarioDAO();
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "cuadrado")
    public double cuadrado(double num) {
        return num * num;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "consutaBaseDatos")
    public String consutaBaseDatos(@WebParam(name = "param1") int numero) {
        //TODO write your implementation code here:
        
        System.out.println("Paso 1...");
        try{
        resultado = usr.consultarUsuario();
        System.out.println("Paso 2...");
        if (resultado == 0) {
            return "Resultado Nulo";
        } else {
            resultado = resultado * numero;
            return "El resultado es: " + resultado;
        }
        }catch(java.awt.HeadlessException d){
            System.out.println(" Error en "  + d.getMessage());
            return "Error";
        }
    }
     @WebMethod(operationName = "validarUsuario")
    public int validarUsuario(@WebParam(name = "param1") int numero) {
        //TODO write your implementation code here:
        
        System.out.println("Entro al metodo validarUsuario");
        try{
        resultado = usr.validarUsuario(numero);
        System.out.println("Obtiene respuesta --> " + resultado);
        return resultado;
        
        }catch(java.awt.HeadlessException d){
            System.out.println(" Metodo validarUsuario = Error en "  + d.getMessage());
            return -1;
        }
    }
}
