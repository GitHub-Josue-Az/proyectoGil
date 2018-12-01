
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name = "datos", eager = true)
@RequestScoped
public class Datos implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Notas> getNotase() {
		ResultSet rs;
		PreparedStatement pst;
		Connection con = getConnectione();
String stm="select n.idNota,n.idCurso,n.idAlumno,n.nota1,n.nota2,n.nota3,n.notaPromedio,c.cursoNombre,c.cursoProfesor,a.alumNombre,a.alumApellido,a.alumCorreo,a.alumDni from notas n,cursos c,alumnos a where n.idCurso=c.idCurso and n.idAlumno=a.idAlumno  ";
		List<Notas> records = new ArrayList<>();
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				Notas n = new Notas();
                                
				n.setIdNota(rs.getInt(1));
				n.setIdCursoNo(rs.getInt(2));
                                n.setIdAlumnoNo(rs.getInt(3));
                                n.setNota1(rs.getInt(4));
                                n.setNota2(rs.getInt(5));
                                n.setNota3(rs.getInt(6));
                                n.setNotaPromedio(rs.getInt(7));
                                n.setCursoNombre(rs.getString(8));
                                n.setCursoProfesor(rs.getString(9));
                                n.setAlumNombre(rs.getString(10));
                                n.setAlumApellido(rs.getString(11));
                                n.setAlumCorreo(rs.getString(12));
                                n.setAlumDni(rs.getInt(13));
				records.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
        
        
         public List<Alumnos> getAlumnose() {
		ResultSet rs;
		PreparedStatement pst;
		Connection con = getConnectione();
String stm="select idAlumno,alumNombre,alumApellido,alumCorreo,alumDni from alumnos";
		List<Alumnos> recor = new ArrayList<>();
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				Alumnos a = new Alumnos();
                                a.setIdAlumno(rs.getInt(1));
                                a.setAlumNombre(rs.getString(2));
                                a.setAlumApellido(rs.getString(3));
                                a.setAlumCorreo(rs.getString(4));
                                a.setAlumDni(rs.getInt(5));
				recor.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recor;
	}
        
         
         public List<Cursos> getCursose() {
		ResultSet rs;
		PreparedStatement pst;
		Connection con = getConnectione();
String stm="select idCurso,cursoNombre,cursoProfesor from cursos";
		List<Cursos> record = new ArrayList<>();
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				Cursos c = new Cursos();
                                c.setIdCurso(rs.getInt(1));
                                c.setCursoNombre(rs.getString(2));
                               c.setCursoProfesor(rs.getString(3));
				record.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}
         
         
        public String deleteCurso(int idC){
            String render;
		PreparedStatement pst;
		Connection conn = getConnectione();
            try{
                String st="delete from cursos where idCurso=?";
            pst = conn.prepareStatement(st);
            pst.setInt(1,idC);
            pst.executeUpdate();
            render="listadoCursos";
              
      }catch(Exception e){
            FacesMessage msg=new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "ERROR EN EL PROCESO",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msg);
                render="cursosMenu";  
      }
            return render;
            
        }      
        
        
        public String deleteNota(int idN){
            String render;
		PreparedStatement pst;
		Connection conn = getConnectione();
            try{
                String st="delete from notas where idNota=?";
            pst = conn.prepareStatement(st);
            pst.setInt(1,idN);
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
        
        
        public String deleteAlumnos(int idA){
            String render;
		PreparedStatement pst;
		Connection conn = getConnectione();
            try{
                String st="delete from alumnos where idAlumno=?";
            pst = conn.prepareStatement(st);
            pst.setInt(1,idA);
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
         
        
         public String listarNotas(){
           return "listadoNotas";
        }
   
         public String listarCursos(){
           return "listadoCursos";
        }
         
         public String listarAlumnos(){
           return "listadoAlumnos";
        }
        
         
     public Connection getConnectione() {
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