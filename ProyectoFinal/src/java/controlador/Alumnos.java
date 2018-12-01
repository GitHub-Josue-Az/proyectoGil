package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
@ManagedBean
@RequestScoped
public class Alumnos extends Cursos{

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getAlumNombre() {
        return alumNombre;
    }

    public void setAlumNombre(String alumNombre) {
        this.alumNombre = alumNombre;
    }

    public String getAlumApellido() {
        return alumApellido;
    }

    public void setAlumApellido(String alumApellido) {
        this.alumApellido = alumApellido;
    }

    public String getAlumCorreo() {
        return alumCorreo;
    }

    public void setAlumCorreo(String alumCorreo) {
        this.alumCorreo = alumCorreo;
    }

    public int getAlumDni() {
        return alumDni;
    }

    public void setAlumDni(int alumDni) {
        this.alumDni = alumDni;
    }
    
    int idAlumno;
    String alumNombre;
    String alumApellido;
    String alumCorreo;
    int alumDni;
    
    
    public String registrarAlumno(){
               String render;
		PreparedStatement pst;
		Connection conn = getConnections();
            try{
                String st="insert into alumnos values(?,?,?,?,?)";
            pst = conn.prepareStatement(st);
            pst.setInt(1,idAlumno );
            pst.setString(2,alumNombre );
            pst.setString(3,alumApellido );
            pst.setString(4, alumCorreo);
            pst.setInt(5,alumDni );
            pst.executeUpdate();
            render="listadoAlumnos";
              
      }catch(Exception e){
            FacesMessage msg=new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "ERROR EN EL PROCESO",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msg);
                render="AlumnosMenu";  
      }
            return render;
    }
    
	public Connection getConnections() {
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
    
}
