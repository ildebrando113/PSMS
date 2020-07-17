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

import it.enaip.stage.model.User;
import it.enaip.stage.model.User.Status;



public class DaoUser implements UserDao {
	private DaoUser(){};
	private static class SingletonHelper {
		private static final DaoUser INSTANCE = new DaoUser();
	}

	public static DaoUser getInstance() {
		return SingletonHelper.INSTANCE;
	}

	@Override
	public Optional<User> find(Integer id) throws SQLException {
		Connection conn = null;
		ResultSet resultSet=null;
		PreparedStatement stmt=null;
		try {
			conn = DataSourceFactory.getConnection();
			stmt = conn
				.prepareStatement("SELECT id,name,surname,birthdate,creationtime,age,type FROM users WHERE id=?");

		int age = 0;
		String name = "";
		String surname = "";
		Date birthdate = null;
		Timestamp creationtime = null;
		Status type = null;
		stmt.setInt(1, id);
	    resultSet = stmt.executeQuery();

		if (resultSet.next()) {
			id = resultSet.getInt("id");
			name = resultSet.getString("name");
			surname = resultSet.getString("surname");
			birthdate = resultSet.getDate("birthdate");
			creationtime = resultSet.getTimestamp("creationtime");
			age = resultSet.getInt("age");
			String fromDB = resultSet.getString("type");
			if (fromDB.contains("C")) {
				type = Status.C;
			} else if (fromDB.contains("O")) {
				type = Status.O;
			} else if (fromDB.contains("S")) {
				type = Status.S;
			} else {
				return null;
			}
		}
		
		return Optional.of(new User(id, name, surname, birthdate, creationtime, age, type));
		}finally {
			 try { resultSet.close(); } catch (Exception e) { /* ignored */ }
			 try { stmt.close(); } catch (Exception e) { /* ignored */ }
			 try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}

	@Override
	public List<User> findAll() throws SQLException {
		Connection conn= null;
		PreparedStatement stmt= null;
		ResultSet resultSet = null;
		Status type = null;
		List<User> listuser = new ArrayList<>();
		try {
		 conn = DataSourceFactory.getConnection();
		 stmt = conn
				.prepareStatement("select  id,name,surname,birthdate,creationtime,age,type from users");
		 resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String surname = resultSet.getString("surname");
			Date birthdate = resultSet.getDate("birthdate");
			Timestamp creationtime = resultSet.getTimestamp("creationtime");
			int age = resultSet.getInt("age");
			String fromDB = resultSet.getString("type");
			if (fromDB.contains("C")) {
				type = User.Status.C;
			} else if (fromDB.contains("O")) {
				type = User.Status.O;
			} else if (fromDB.contains("S")) {
				type = User.Status.S;
			} else {
				
				return null;
			}
			User user = new User(id, name, surname, birthdate, creationtime, age, type);
			listuser.add(user);
		}
		return listuser;
		}finally {
			try { resultSet.close(); } catch (Exception e) { /* ignored */ }
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
			
		}
		
		
		
	}

	@Override
	public boolean update(User user) throws SQLException {
		Connection conn= null;
		PreparedStatement stmt= null;
		boolean rowUpdated = false;
		try {
		 conn = DataSourceFactory.getConnection();
		 stmt = conn.prepareStatement(
				"UPDATE  users SET name=?,surname=?,birthdate=?,creationtime=?,age=?,type=? WHERE id=?");
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getSurname());
		java.sql.Date sqlDatebirthdate = new java.sql.Date(user.getBirthdate().getTime());
		stmt.setDate(3, sqlDatebirthdate);
		stmt.setTimestamp(4, user.getCreationtime());
		stmt.setInt(5, user.getAge());
		stmt.setString(6, user.getType().toString());
		stmt.setInt(7, user.getId());
		rowUpdated = stmt.executeUpdate() > 0;
		return rowUpdated;
		}finally {
			
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}

	@Override
	public boolean save(User user) throws SQLException {
		Connection conn= null;
		PreparedStatement stmt= null;
		
		boolean rowInserted = false;
		DaoUser da = DaoUser.getInstance();
		int index = da.getMaxIndex();
		index += 1;
		user.setId(index);
		
		try {
		conn = DataSourceFactory.getConnection();
		if (user.getName() == null) {
			throw new IllegalArgumentException("Username cannot be null");
		}
		if (user.getName().isEmpty()) {
			throw new IllegalArgumentException("UserName cannot be empty");
		}
		if (user.getSurname() == null) {
			throw new IllegalArgumentException("UserSurname cannot be null");
		}

		if (user.getSurname().isEmpty()) {
			throw new IllegalArgumentException("UserSurname cannot be empty");
		}
		if (user.getBirthdate() == null) {
			throw new IllegalArgumentException("Birthdate cannot be null");
		}
		if (user.getCreationtime() == null) {
			throw new IllegalArgumentException("Creationtime cannot be null");
		}
		if (user.getCreationtime() == null) {
			throw new IllegalArgumentException("Creationtime cannot be null");
		}
		if (user.getAge() < 0) {
			throw new IllegalArgumentException("Age cannot be negative");
		}
		 stmt = conn.prepareStatement(
				"INSERT INTO users (name,surname,birthdate,creationtime,age,type,id) VALUES (?,?,?,?,?,?,?)");
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getSurname());
		java.sql.Date sqlDatebirthdate = new java.sql.Date(user.getBirthdate().getTime());
		stmt.setDate(3, sqlDatebirthdate);
		stmt.setTimestamp(4, user.getCreationtime());
		stmt.setInt(5, user.getAge());
		stmt.setString(6, user.getType().toString());
		stmt.setInt(7, user.getId());
		rowInserted = stmt.executeUpdate() > 0;
		return rowInserted;
		}finally {
			
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
			
		}
	}

	@Override
	public boolean delete(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean rowDeleted = false;
		try {
	    conn = DataSourceFactory.getConnection();
	    stmt = conn.prepareStatement("DELETE FROM users Where id=?");
		stmt.setInt(1, user.getId());
		rowDeleted = stmt.executeUpdate() > 0;
		return rowDeleted;
		}finally {
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		
	}

	public User findUser(Integer id) throws SQLException {
		Connection conn = null;
		ResultSet resultSet=null;
		PreparedStatement stmt=null;
		int age = 0;
		String name = "";
		String surname = "";
		Date birthdate = null;
		Timestamp creationtime = null;
		Status type = null;
		try {
		 conn = DataSourceFactory.getConnection();
		 stmt = conn
				.prepareStatement("SELECT id,name,surname,birthdate,creationtime,age,type FROM users WHERE id=?");
	
		stmt.setInt(1, id);
		 resultSet = stmt.executeQuery();
		if (resultSet.next()) {
			id = resultSet.getInt("id");
			name = resultSet.getString("name");
			surname = resultSet.getString("surname");
			birthdate = resultSet.getDate("birthdate");
			creationtime = resultSet.getTimestamp("creationtime");
			age = resultSet.getInt("age");
			String fromDB = resultSet.getString("type");
			if (fromDB.contains("C")) {
				type = Status.C;
			} else if (fromDB.contains("O")) {
				type = Status.O;
			} else if (fromDB.contains("S")) {
				type = Status.S;
			} else {
				
				return null;
			}
		}
		
		return (new User(id, name, surname, birthdate, creationtime, age, type));
		}finally {
			try { resultSet.close(); } catch (Exception e) { /* ignored */ }
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
			
		}
	}

	public int getMaxIndex() throws SQLException {
		int maxID = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			conn = DataSourceFactory.getConnection();
		    stmt = conn.createStatement();
			stmt.execute("SELECT MAX(id) FROM users");
			resultSet = stmt.getResultSet(); //
			if (resultSet.next()) {
				maxID = resultSet.getInt(1);
			}
		
		return maxID;
		}finally {
			try { resultSet.close(); } catch (Exception e) { /* ignored */ }
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
			
		}
	}

	public boolean checkLogin(String uname, String pass) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			conn = DataSourceFactory.getConnection();
			stmt = conn.prepareStatement("select * from login where username =? and password =?");
			stmt.setString(1, uname);
			stmt.setString(2, pass);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		return false;
		}finally {
			try { resultSet.close(); } catch (Exception e) { /* ignored */ }
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
			try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}
}