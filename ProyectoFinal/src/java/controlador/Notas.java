package controlador;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@RequestScoped
public class Notas extends Alumnos {

    public int getIdCursoNo() {
        return idCursoNo;
    }

    public void setIdCursoNo(int idCursoNo) {
        this.idCursoNo = idCursoNo;
    }

    public int getIdAlumnoNo() {
        return idAlumnoNo;
    }

    public void setIdAlumnoNo(int idAlumnoNo) {
        this.idAlumnoNo = idAlumnoNo;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }


    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public int getNota3() {
        return nota3;
    }

    public void setNota3(int nota3) {
        this.nota3 = nota3;
    }

    public int getNotaPromedio() {
        return notaPromedio;
    }

    public void setNotaPromedio(int notaPromedio) {
        this.notaPromedio = notaPromedio;
    }
     
    
     int idNota;
     int idCursoNo;
     int idAlumnoNo;
     int nota1;
     int nota2;
     int nota3;
     int notaPromedio;  
     
     
     public String registrarNota(){
               String render;
		PreparedStatement pst;
		Connection conn = getConnectionn();
            try{
                
               validar(nota1);
               validar(nota2);
               validar(nota3);
                
            String st="insert into notas values(?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(st);
            pst.setInt(1,idNota);
            pst.setInt(2,idCursoNo);
            pst.setInt(3,idAlumnoNo);
            pst.setInt(4,nota1);
            pst.setInt(5, nota2);
            pst.setInt(6, nota3);
            pst.setInt(7,notaPromedio);
            pst.executeUpdate();
            render="listadoNotas";
              
      }catch(Exception e){
            FacesMessage msg=new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "ERROR EN EL PROCESO",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msg);
                render="notasMenu";  
      }
            return render;
    }
        
	public Connection getConnectionn() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/trabajofinal";
		String user = "root";
		String password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connection completed");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
		}
		return con;
	 }
        
        public void validar(int num){
        if(num<0){
            throw new RuntimeException("No permite valores negativos");
         }
        if(num>100){
            throw new RuntimeException("No permite valores superiores a 100");
        }
     
        }
}
