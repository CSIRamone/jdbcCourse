package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    Connection conn = null;
	    PreparedStatement pt = null;
	    
	    try {
	    	conn = DB.getConnection();
	    	
	    	pt = conn.prepareStatement(
	    			"Insert into seller"
	    			+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
	    			+ "values"
	    			+ "(?, ?, ?, ?, ?)",
	    			Statement.RETURN_GENERATED_KEYS);
	    	
	    	pt.setString(1, "Carl Purple");
	    	pt.setString(2, "carl@gmail.com");
            pt.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
            pt.setDouble(4, 3000.0);
            pt.setInt(5, 4);
            
            int rowsAffected = pt.executeUpdate();
            
            if (rowsAffected > 0) {
            	ResultSet rs  = pt.getGeneratedKeys();
            	while (rs.next()) {
            		int id = rs.getInt(1);
            		System.out.println("Done! Id = " + id);
            	}
            }
            else {
            	System.out.println("No rows Affected!");
            }
	    	
	    			
	    }
	    catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    catch (ParseException e ) {
	    	e.printStackTrace();
	    }
	    finally {
	    	DB.closeStatement(pt);
	    	DB.closeConnection();
	    	
	    }
	    
	   
	    
	  
	}

}
