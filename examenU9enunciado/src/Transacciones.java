

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transacciones {
    public static void main(String[] args) {
       Connection con=Conexion.conectar();

        try{
            con.setAutoCommit(false);

            Employee empleado1 = new Employee(
                    9005,
                    "Alonso",
                    "Fernando",
                    "8181",
                    "alo@classicmodelcars.com",
                    "4",
                    1102,
                    "Piloto");
            
            Employee empleado2 = new Employee(
                    9006,
                    "Newey",
                    "Adrian",
                    "8181",
                    "alo@classicmodelcars.com",
                    "4",
                    1102,
                    "Ingeniero");
            
            // COMPLETAR AQUI. Inserta en BBDD los 2 empleados
            EmployeeModel.insertEmployee(con, empleado1);
            EmployeeModel.insertEmployee(con, empleado2);

            System.out.println("Insertados 2 empleados");
            
            empleado1.setOfficeCode("5");
            
            // COMPLETAR AQUI. Modifica en BBDD la oficina del empleado 1
            EmployeeModel.updateEmployeeOffice(con, empleado1);

            System.out.println("Modificada oficina del empleado 1");  
            
            // COMPLETAR AQUI. Borra de BBDD el empleado 2
            EmployeeModel.deleteEmployee(con, empleado2);
            
            System.out.println("Borrado empleado 2");  

            con.commit();
               
        }catch (SQLException e){
            System.out.println("error: " +e.getMessage());
            try{
                if (con!=null){
                    System.out.println("Dejamos la BBDD en estado consistente.");
                    con.rollback();
                }
            } catch (SQLException throwables){
                System.out.println("Error en el rollback");
                throwables.printStackTrace();
            }
        }
    }
}
