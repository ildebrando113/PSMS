package it.enaip.stage.dao;

import it.enaip.stage.model.Stuff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DaoStuff implements StuffDao{
    
    private DaoStuff(){}

    private static class SingletonHelper{
        
        private static final DaoStuff INSTANCE = new DaoStuff();
    }
    
    public static DaoStuff getInstance(){
        return SingletonHelper.INSTANCE;
    }
    
    
    @Override
    public Optional<Stuff> find (String id) throws SQLException{
       
       Connection conn =DataSourceFactory.getConnection();
       PreparedStatement stmt=conn.prepareStatement("SELECT stuff_id,name,description,quantity,location FROM stuff WHERE stuff_id=?");
       //String sql= "SELECT stuff_id,name,description,location FROM stuff WHERE stuff_id=?";
       int id_stuff =0,quantity=0,i;
       String name="",description="",location="";
       stmt.setString(1, id);
       ResultSet resultSet = stmt.executeQuery();
      
      
       if(resultSet.next()){
           id_stuff = resultSet.getInt("stuff_id");
           name= resultSet.getString("name");
           description= resultSet.getString("description");
           quantity= resultSet.getInt("quantity");
           location= resultSet.getString("location");
        }
       return Optional.of(new Stuff(id_stuff,name,description,quantity,location));
       
        
    }
    @Override
    public List<Stuff> findAll() throws SQLException{
        
        List<Stuff> liststuff = new ArrayList<>();
        Connection conn =DataSourceFactory.getConnection();
        PreparedStatement stmt=conn.prepareStatement("select  STUFF_ID,NAME,DESCRIPTION,QUANTITY,LOCATION from STUFF");
        ResultSet resultSet = stmt.executeQuery();
         while (resultSet.next()){
          int id_stuff = resultSet.getInt("stuff_id");
          String name= resultSet.getString("name");
          String description= resultSet.getString("description");
          int quantity= resultSet.getInt("quantity");
          String location= resultSet.getString("location");
          Stuff stuff = new  Stuff(id_stuff,name,description,quantity,location);
          liststuff.add(stuff);
           
        }
        
       return liststuff; 
    }
    @Override
    public boolean update (Stuff stuff) throws SQLException{
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
    public boolean save (Stuff stuff) throws SQLException{
        boolean rowInserted= false;
        DaoStuff da= DaoStuff.getInstance();
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
    public boolean delete (Stuff stuff) throws SQLException{
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
