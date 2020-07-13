package it.enaip.stage.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import it.enaip.stage.dao.DaoUser;

import it.enaip.stage.model.User;
import it.enaip.stage.model.User.Status;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoUser UserDao = DaoUser.getInstance();
	private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {

		String op = req.getParameter("op");
		try {
			switch (op) {
			case "new":
				showNewForm(req, resp);
				break;
			case "insert":
				insertUser(req, resp);
				break;
			case "delete":
				deleteUser(req, resp);
				break;
			case "edit":
				showEditForm(req, resp);
				break;
			case "update":
				updateUser(req, resp);
				break;
			
			default:
				userList(req, resp);
				break;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "SQL Error", e);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	}

	private void updateUser(HttpServletRequest req, HttpServletResponse resp)
	throws ParseException, IOException, SQLException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String date = req.getParameter("birthdate");
		Date birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		String time = req.getParameter("creationtime");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date parsedDate = dateFormat.parse(time);
		Timestamp creationtime = new java.sql.Timestamp(parsedDate.getTime());
		int age = Integer.parseInt(req.getParameter("age"));
		String tipo = req.getParameter("type");
		Status type = Status.valueOf(tipo);
		User user = new User(id, name, surname, birthdate, creationtime, age, type);
		UserDao.update(user);
		resp.sendRedirect("UserController?op=list");

	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
	throws SQLException, ServletException, IOException {
		
		String id = req.getParameter("id");
		int index = Integer.parseInt(id);
		Optional<User> existingUser = UserDao.find(index);
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/UserForm.jsp");
		existingUser.ifPresent(s -> req.setAttribute("user", s));
		dispatcher.forward(req, resp);

	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) 
	throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		User user = new User(id);
		UserDao.delete(user);
		resp.sendRedirect("UserController?op=list");

	}

	private void insertUser(HttpServletRequest req, HttpServletResponse resp)
			throws ParseException, SQLException, IOException {

		int id = 0;
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String date = req.getParameter("birthdate");
		Date birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		String time = req.getParameter("creationtime");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date parsedDate = dateFormat.parse(time);
		Timestamp creationtime = new java.sql.Timestamp(parsedDate.getTime());
		int age = Integer.parseInt(req.getParameter("age"));
		String tipo = req.getParameter("type");
		Status type = Status.valueOf(tipo);
		User newuser = new User(id, name, surname, birthdate, creationtime, age, type);
		UserDao.save(newuser);
		resp.sendRedirect("UserController?op=list");

	}

	public void showNewForm(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/UserForm.jsp");
		dispatcher.forward(req, resp);
	}

	private void userList(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException, SQLException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/UserList.jsp");
		List<User> userList = UserDao.findAll();
		req.setAttribute("userList", userList);
		dispatcher.forward(req, resp);
	}
	
	public JSONObject getJson(String id) throws SQLException, JSONException {
		User user = new User();
		int i = Integer.parseInt(id);
		DaoUser dao = DaoUser.getInstance();
		user =  dao.findUser(i);
		JSONObject jobj = user.getJsonObject();
		return jobj;
		
	}
}
