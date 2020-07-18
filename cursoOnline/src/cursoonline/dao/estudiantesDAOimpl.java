package cursoonline.dao;

import curso.util.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cursoonline.entidades.Estudiante;


public class estudiantesDAOimpl implements estudianteDAO {

	@Override
	public void ingresar(Estudiante estudiante) {
		String query ="INSERT INTO public.estudiantes(nombres, apellidos, email)VALUES ( ?, ?, ?);";
		Connection conn;
		try
	       {
		   conn = DriverManager.getConnection(util.url, util.user, util.password);
		   		 

			PreparedStatement stm = conn.prepareStatement(query);  
			stm.setString(1, estudiante.getNombre());
			stm.setString(2, estudiante.getApellidos());
			stm.setString(3, estudiante.getEmail());
			stm.execute();
			   
		   }
		   	   
	   catch (SQLException ex) {
		// TODO Auto-generated catch block
		ex.printStackTrace();
	}
		
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		String query="UPDATE public.estudiantes SET  nombres=?, apellidos=?, email=? WHERE  id= ?;";
		
		Connection conn;
		try
	       {
		   conn = DriverManager.getConnection(util.url, util.user, util.password);
		   		 

			PreparedStatement stm = conn.prepareStatement(query);  
			stm.setString(1, estudiante.getNombre());
			stm.setString(2, estudiante.getApellidos());
			stm.setString(3, estudiante.getEmail());
			stm.setInt(4, estudiante.getId());
			stm.execute();
			   
		   }
		   	   
	   catch (SQLException ex) {
		// TODO Auto-generated catch block
		ex.printStackTrace();
	}
		
		
		
		
		
	}

	@Override
	public void eliminar(int id) {
		String query ="DELETE FROM public.estudiantes WHERE id=?;";
		Connection conn;
		try
	       {
		   conn = DriverManager.getConnection(util.url, util.user, util.password);
		   		 

			PreparedStatement stm = conn.prepareStatement(query);  
			stm.setInt(1, id);
			stm.execute();
			   
		   }
		   	   
	   catch (SQLException ex) {
		// TODO Auto-generated catch block
		ex.printStackTrace();
	}
		
	}

	@Override
	public List<Estudiante> getEstudiante() {
		 String sql = "SELECT id, nombres, apellidos, email FROM estudiantes";
		List<Estudiante> estudiante = new ArrayList<>(); 
		Connection con; 
	        
         try 
       {
           con = DriverManager.getConnection(util.url, util.user, util.password);
           
           
      
          
           
           PreparedStatement stm = con.prepareStatement(sql);
           ResultSet rs = stm.executeQuery();
           while(rs.next()){
              Estudiante estudiant = new Estudiante(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4));
              estudiante.add(estudiant);        
            }       
           
       }   
         catch (SQLException ex) 
         
       {
       	 ex.printStackTrace();
       }	
	
		
		return estudiante;
	}
	
}