package app;

import java.io.*;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import data.Admin;

@WebServlet(urlPatterns = {"/addadmin", "/deleteadmin","/updateadmin","/readadmin","/readtoupdateadmin","/showadmin"})
public class HandleAdmin extends HttpServlet {

	  @Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
		  doGet(request, response);
	  }
	  
	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
	  String action = request.getServletPath();
	  List<Admin> list=null;
	  switch (action) {
	  case "/addadmin":
		  list=addadmin(request);break;
	  case "/deleteadmin":
		  String name=request.getParameter("name");
		  list=deleteadmin(request);break;
	  case "/updateadmin":
		  list=updateadmin(request);break;
	  case "/readadmin":
		  list=readadmin(request);break;

	  case "/readtoupdateadmin":
		  Admin q=readtoupdateadmin(request);
		  request.setAttribute("admin", q);
		  RequestDispatcher rd=request.getRequestDispatcher("./jsp/admintoupdateform.jsp");
		  rd.forward(request, response);
		  return;
	  }
	  request.setAttribute("adminlist", list);
	  RequestDispatcher rd=request.getRequestDispatcher("./jsp/editadmin.jsp");

	  rd.forward(request, response);
  }

	private Admin readtoupdateadmin(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/adminservice/readtoupdateadmin/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Admin admin=b.get(Admin.class);
		return admin;
	}

	private List<Admin> addadmin(HttpServletRequest request) {
		//A Admin object to send to our web-service 
		Admin f=new Admin(request.getParameter("firstname"),request.getParameter("lastname"),request.getParameter("username"),request.getParameter("email"),request.getParameter("password"));
		System.out.println(f);
		String uri = "http://127.0.0.1:8080/rest/adminservice/addadmin";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a Admin object as JSON string format
		Entity<Admin> e=Entity.entity(f,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Admin>> genericList = new GenericType<List<Admin>>() {};
		
		//Posting data (Entity<ArrayList<Admin>> e) to the given address
		List<Admin> returnedList=b.post(e, genericList);
		return returnedList;
	}
	
	private List<Admin> readadmin(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/adminservice/readadmin";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Admin>> genericList = new GenericType<List<Admin>>() {};
		
		List<Admin> returnedList=b.get(genericList);
		return returnedList;
	}
	
	private List<Admin> updateadmin(HttpServletRequest request) {
		//A Admin object to send to our web-service 
		Admin q=new Admin(request.getParameter("firstname"),request.getParameter("lastname"),request.getParameter("username"),request.getParameter("email"),request.getParameter("password"));
		System.out.println(q);
		String uri = "http://127.0.0.1:8080/rest/adminservice/updateadmin";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a Admin object as JSON string format
		Entity<Admin> e=Entity.entity(q,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Admin>> genericList = new GenericType<List<Admin>>() {};
		
		//Posting data (Entity<ArrayList<Admin>> e) to the given address
		List<Admin> returnedList=b.put(e, genericList);
		return returnedList;
	}
	
	private List<Admin> deleteadmin(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/adminservice/deleteadmin/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Admin>> genericList = new GenericType<List<Admin>>() {};
		
		//Posting data (Entity<ArrayList<Admin>> e) to the given address
		List<Admin> returnedList=b.delete(genericList);
		return returnedList;
	}
}