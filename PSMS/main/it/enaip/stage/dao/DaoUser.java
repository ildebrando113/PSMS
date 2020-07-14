package it.enaip.stage.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

import it.enaip.stage.model.User;
import it.enaip.stage.model.User.Status;
import it.enaip.stage.model.userToJson;





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
       PreparedStatement stmt=conn.prepareStatement("SELECT id,name,surname,birthdate,creationtime,age,type FROM users WHERE id=?");
       //String sql= "SELECT stuff_id,name,description,location FROM stuff WHERE stuff_id=?";
       int age=0;
       String name="";
       String surname="";
       Date birthdate=null;
       Timestamp creationtime=null;
       Status type=null;
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
        	   type = Status.C;
           }else if (fromDB.contains("O")) {
        	   type = Status.O;
           }else if (fromDB.contains("S")) {
        	   type = Status.S;
           }
           else{
        	   return null;
           }
           
        }
       
       
       return Optional.of(new User(id,name,surname,birthdate,creationtime,age,type));
       
        
    }
    @Override
    public List<User> findAll() throws SQLException{
        Status type=null;
        
        List<User> listuser= new ArrayList<>();
        Connection conn =DataSourceFactory.getConnection();
        PreparedStatement stmt=conn.prepareStatement("select  id,name,surname,birthdate,creationtime,age,type from users");
        ResultSet resultSet = stmt.executeQuery();
         while (resultSet.next()){
          int id = resultSet.getInt("id");
          String name= resultSet.getString("name");
         String surname= resultSet.getString("surname");
         Date birthdate = resultSet.getDate("birthdate");
       
          Timestamp creationtime = resultSet.getTimestamp("creationtime");
        int  age= resultSet.getInt("age");
        String fromDB = resultSet.getString("type");
        if (fromDB.contains("C")) {
     	   type = User.Status.C;
     	   
        }else if (fromDB.contains("O")) {
        	type = User.Status.O;
        }else if (fromDB.contains("S")) {
        	type = User.Status.S;
        }
        else{
     	   return null;
        }
        
          User user = new  User(id, name, surname, birthdate, creationtime, age, type);
          listuser.add(user);
           
        }
        
       return listuser; 
    }
    @Override
    public boolean update (User user) throws SQLException{
        boolean rowUpdated= false;
        Connection conn =DataSourceFactory.getConnection();
        PreparedStatement stmt=conn.prepareStatement("UPDATE  users SET name=?,surname=?,birthdate=?,creationtime=?,age=?,type=? WHERE id=?");
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getSurname());
        java.sql.Date sqlDatebirthdate = new java.sql.Date(user.getBirthdate().getTime());
        stmt.setDate(3, sqlDatebirthdate);
        stmt.setTimestamp(4, user.getCreationtime());
        stmt.setInt(5, user.getAge());
        stmt.setString(6, user.getType().toString());
        stmt.setInt(7,user.getId());
        rowUpdated = stmt.executeUpdate()>0;
        return rowUpdated;
        
    }
    @Override
    public boolean save (User user) throws SQLException{
        boolean rowInserted= false;
        DaoUser da= DaoUser.getInstance();
        int index= da.getMaxIndex();
        index+=1;
        user.setId(index);
        try {
        	
        
        Connection conn =DataSourceFactory.getConnection();
       
        PreparedStatement stmt=conn.prepareStatement("INSERT INTO users (name,surname,birthdate,creationtime,age,type,id) VALUES (?,?,?,?,?,?,?)");
       
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getSurname());
        java.sql.Date sqlDatebirthdate = new java.sql.Date(user.getBirthdate().getTime());
        stmt.setDate(3, sqlDatebirthdate);
        stmt.setTimestamp(4, user.getCreationtime());
        stmt.setInt(5, user.getAge());
        stmt.setString(6, user.getType().toString());
        stmt.setInt(7,user.getId());
        rowInserted = stmt.executeUpdate()>0;
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        	System.out.println(e.getStackTrace());
        	} 
        return rowInserted;
        
    }
    
    @Override
    public boolean delete (User user) throws SQLException{
        boolean rowDeleted = false;
        Connection conn =DataSourceFactory.getConnection();
        PreparedStatement stmt=conn.prepareStatement("DELETE FROM users Where id=?");
        stmt.setInt(1,user.getId());
        rowDeleted = stmt.executeUpdate()>0;
        return rowDeleted;
                
    }
    public int getMaxIndex() throws SQLException {
    	int maxID=0;
    	try {
    		 Connection conn =DataSourceFactory.getConnection();
    		 Statement stmt = conn.createStatement();
    		 stmt.execute("SELECT MAX(id) FROM users");
    		 ResultSet rs = stmt.getResultSet(); // 
    				  if (rs.next()) {
    					   maxID = rs.getInt(1);
    					}
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
		return maxID;
    }
    
    public User findUser(Integer id ) throws SQLException{
    	Connection conn =DataSourceFactory.getConnection();
        PreparedStatement stmt=conn.prepareStatement("SELECT id,name,surname,birthdate,creationtime,age,type FROM users WHERE id=?");
        //String sql= "SELECT stuff_id,name,description,location FROM stuff WHERE stuff_id=?";
        int age=0;
        String name="";
        String surname="";
        Date birthdate=null;
        Timestamp creationtime=null;
        Status type=null;
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
         	   type = Status.C;
            }else if (fromDB.contains("O")) {
         	   type = Status.O;
            }else if (fromDB.contains("S")) {
         	   type = Status.S;
            }
            else{
         	   return null;
            }
            
            
            
            
         }
        return (new User(id,name,surname,birthdate,creationtime,age,type));
        
     }
	
}

