package it.enaip.stage.dao;

import it.enaip.stage.model.User;
import it.enaip.stage.model.User.Status;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class DaoUser implements UserDao {
    
    private DaoUser(){}

    private static class SingletonHelper{
        
        private static final DaoUser INSTANCE = new DaoUser();
    }
    
    public static DaoUser getInstance(){
        return SingletonHelper.INSTANCE;
    }
    
    
    @Override
    public Optional<User> find(Integer id) throws SQLException{
       
       Connection conn =DataSourceFactory.getConnection();
       PreparedStatement stmt=conn.prepareStatement("SELECT id,name,surname,birthdate,creationtime,age,type FROM stuff WHERE stuff_id=?");
       //String sql= "SELECT stuff_id,name,description,location FROM stuff WHERE stuff_id=?";
       int age=0;
       String name="";
       String surname;
       Date birthdate;
       Timestamp creationtime;
       Status type;
       stmt.setInt(1, id);
       ResultSet resultSet = stmt.executeQuery();
      
      
       if(resultSet.next()){
           id = resultSet.getInt("id");
           name= resultSet.getString("name");
           surname= resultSet.getString("surname");
           birthdate = resultSet.getDate("birthdate");
           creationtime = resultSet.getTimestamp("creationtime");
           age= resultSet.getInt("age");
           String fromDB = resultSet.getString("type");
           if (fromDB.contains("C")) {
        	   type = type.CHILD;
           }else if (fromDB.contains("O")) {
        	   type = type.OWNER;
           }else if (fromDB.contains("S")) {
        	   type = type.SPOUSE;
           }
           else{
        	   return null;
           }
           
        }
       return Optional.of(new User(id,name,surname,birthdate,creationtime,age,type));
       
        
    }
    @Override
    public List<User> findAll() throws SQLException{
        
        List<User> liststuff = new ArrayList<>();
        Connection conn =DataSourceFactory.getConnection();
        PreparedStatement stmt=conn.prepareStatement("select  STUFF_ID,NAME,DESCRIPTION,QUANTITY,LOCATION from STUFF");
        ResultSet resultSet = stmt.executeQuery();
         while (resultSet.next()){
          int id_stuff = resultSet.getInt("stuff_id");
          String name= resultSet.getString("name");
          String description= resultSet.getString("description");
          int quantity= resultSet.getInt("quantity");
          String location= resultSet.getString("location");
          User stuff = new  User(id_stuff,name,description,quantity,location);
          liststuff.add(stuff);
           
        }
        
       return liststuff; 
    }
    @Override
    public boolean update (User stuff) throws SQLException{
        boolean rowUpdated= false;
        Connection conn =DataSourceFactory.getConnection();
        PreparedStatement stmt=conn.prepareStatement("UPDATE  stuff SET name=?,description=?,quantity=?,location=? WHERE stuff_id=?");
        stmt.setString(1, stuff.getName());
        stmt.setString(2, stuff.getDescription());
        stmt.setInt(3, stuff.getQuantity());
        stmt.setString(4, stuff.getLocation());
        stmt.setInt(5, stuff.getId());
        rowUpdated = stmt.executeUpdate()>0;
        return rowUpdated;
        
    }
    @Override
    public boolean save (User stuff) throws SQLException{
        boolean rowInserted= false;
        DaoUser da= DaoUser.getInstance();
        int index= da.getMaxIndex();
        index+=1;
        stuff.setId(index);
        try {
        	
        
        Connection conn =DataSourceFactory.getConnection();
       
        PreparedStatement stmt=conn.prepareStatement("INSERT INTO stuff (name,description,quantity,location,stuff_id) VALUES (?,?,?,?,?)");
       
        stmt.setString(1, stuff.getName());
        stmt.setString(2, stuff.getDescription());
        stmt.setInt(3, stuff.getQuantity());
        stmt.setString(4, stuff.getLocation());
        stmt.setInt(5,stuff.getId());
        rowInserted = stmt.executeUpdate()>0;
        }catch(Exception e) {
        	System.out.println(e.getMessage());} 
        return rowInserted;
        
    }
    
    @Override
    public boolean delete (User stuff) throws SQLException{
        boolean rowDeleted = false;
        Connection conn =DataSourceFactory.getConnection();
        PreparedStatement stmt=conn.prepareStatement("DELETE FROM stuff Where stuff_id=?");
        stmt.setInt(1,stuff.getId());
        rowDeleted = stmt.executeUpdate()>0;
        return rowDeleted;
                
    }
    public int getMaxIndex() throws SQLException {
    	int maxID=0;
    	try {
    		 Connection conn =DataSourceFactory.getConnection();
    		 Statement stmt = conn.createStatement();
    		 stmt.execute("SELECT MAX(stuff_id) FROM stuff");
    		 ResultSet rs = stmt.getResultSet(); // 
    				  if (rs.next()) {
    					   maxID = rs.getInt(1);
    					}
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
		return maxID;
    }


	
}
