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
public class Cursos implements Serializable{

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getCursoNombre() {
        return cursoNombre;
    }

    public void setCursoNombre(String cursoNombre) {
        this.cursoNombre = cursoNombre;
    }

    public String getCursoProfesor() {
        return cursoProfesor;
    }

    public void setCursoProfesor(String cursoProfesor) {
        this.cursoProfesor = cursoProfesor;
    }

   
    int idCurso;
    String cursoNombre;
    String cursoProfesor;
    
    
    public String registrarCurso(){
               String render;
		PreparedStatement pst;
		Connection conn = getConnection();
            try{
                String st="insert into cursos values(?,?,?)";
            pst = conn.prepareStatement(st);
            pst.setInt(1,idCurso );
            pst.setString(2, cursoNombre);
            pst.setString(3,cursoProfesor );
            pst.executeUpdate();
            render="listadoCursos";
              
      }catch(Exception e){
            FacesMessage msg=new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "ERROR EN EL PROCESO",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msg);
                render="paginaInicial";  
      }
            return render;
    }
    
   
	public Connection getConnection() {
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
