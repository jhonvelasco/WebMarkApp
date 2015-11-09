package markApp.prueba;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;

public class ConexionDB {

    static Connection conexion;

    public Connection generaConexion(String URL) {
        conexion = null;
        OracleDataSource ods = null;
        try {
            // Se abre una conexión con la base de datos
            ods = new OracleDataSource();
//            System.out.println("Se crea la instancia");
            ods.setURL(URL);
//            System.out.println("se autentica");
            conexion = ods.getConnection();
//            System.out.println("conexion con exito");
        } catch (Exception e) {
            conexion = null;
            System.out.println("Error en clase de conexion -->" +e.getMessage()+ " \nla URL es "+ URL);
//          JOptionPane.showMessageDialog(null, "Ocurrio este "+e.getMessage());
        }

        return conexion;
    }

    public static ResultSet busqueda(String sql) throws SQLException, IOException {
        //inicio de la conexion        
        Statement stat = conexion.createStatement();
        //Fin de la conexion
        ResultSet result = stat.executeQuery(sql);
        return result;
    }

    /**
     * realiza el recorrido por los diferentes registros
     * resultado de la busqueda y devuelve el campo solicitado
     * @param sql
     * @param numcampos
     */
    public static ArrayList datosBusqueda(String sql, int numcampos) {
        ArrayList lista = new ArrayList();
        String[] datos;
        try {
            ResultSet res = busqueda(sql);
            int i;

            while (res.next()) {
                datos = new String[numcampos];
                for (i = 0; i < numcampos; i++) {
                    datos[i] = res.getString(i + 1);
                }
                lista.add(datos);
            }
            return lista;
        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println("Ex 1"+ex.getMessage());

                ex = ex.getNextException();
            }
            return null;
        } catch (IOException ex) {
            System.out.println("Ex 2"+ex.getMessage());

            return null;
        }
    }

    public static boolean ejecutarSQL(String sql) {
        //inicio de la conexion
        try {
//            Connection conn = getConec();//getConnection();
            Statement stat = conexion.createStatement();
            stat.execute(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            while (ex != null) {

                String eexiste = ex.getMessage().charAt(0) + "" + ex.getMessage().charAt(1) + ex.getMessage().charAt(2) + ex.getMessage().charAt(3) + ex.getMessage().charAt(4) + ex.getMessage().charAt(5) + ex.getMessage().charAt(6) + ex.getMessage().charAt(7) + ex.getMessage().charAt(8);

                if ("ORA-02292".compareTo(eexiste) == 0) {
                    JOptionPane.showMessageDialog(null, "ya esta siendo utilizado en otra tabla");
                }

                if ("ORA-01847".compareTo(eexiste) == 0) {
                    JOptionPane.showMessageDialog(null, "digite un dia correcto ");
                }
                if ("ORA-01843".compareTo(eexiste) == 0) {
                    JOptionPane.showMessageDialog(null, "digite un mes valido ");
                }

                System.out.println(ex.getMessage());
                //JOptionPane.showMessageDialog(null, ex.getMessage());

                ex = ex.getNextException();
            }
            return false;
        } 
    }
}
