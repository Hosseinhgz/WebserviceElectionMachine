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

import model.Candidate;

@WebServlet(urlPatterns = {"/addcandidate", "/deletecandidate","/updatecandidate","/readcandidate","/readtoupdatecandidate","/showcandidate"})
public class HandleCandidate extends HttpServlet {

	  @Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
		  doGet(request, response);
	  }
	  
	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
	  String action = request.getServletPath();
	  List<Candidate> list=null;
	  switch (action) {
	  case "/addcandidate":
		  list=addcandidate(request);break;
	  case "/deletecandidate":
		  list=deletecandidate(request);break;
	  case "/updatecandidate":
		  list=updatecandidate(request);break;
	  case "/readcandidate":
		  list=readcandidate(request);break;
	  case "/showcandidate":
		  list=readcandidate(request);
		  request.setAttribute("candidatelist", list);
		  RequestDispatcher rd2=request.getRequestDispatcher("./jsp/showcandidate.jsp");
		  rd2.forward(request, response);
		  break;
	  case "/readtoupdatecandidate":
		  Candidate q=readtoupdatecandidate(request);
		  request.setAttribute("candidate", q);
		  RequestDispatcher rd=request.getRequestDispatcher("./jsp/candidatetoupdateform.jsp");
		  rd.forward(request, response);
		  return;
	  }
	  request.setAttribute("candidatelist", list);
	  RequestDispatcher rd=request.getRequestDispatcher("./jsp/candidateform.jsp");

	  rd.forward(request, response);
  }

	private Candidate readtoupdatecandidate(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/candidateservice/readtoupdatecandidate/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Candidate candidate=b.get(Candidate.class);
		return candidate;
	}

	private List<Candidate> addcandidate(HttpServletRequest request) {
		//A Candidate object to send to our web-service 
		Candidate f=new Candidate(request.getParameter("id"), request.getParameter("surname"),request.getParameter("firstname"));
		System.out.println(f);
		String uri = "http://127.0.0.1:8080/rest/candidateservice/addcandidate";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a Candidate object as JSON string format
		Entity<Candidate> e=Entity.entity(f,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Candidate>> genericList = new GenericType<List<Candidate>>() {};
		
		//Posting data (Entity<ArrayList<Candidate>> e) to the given address
		List<Candidate> returnedList=b.post(e, genericList);
		return returnedList;
	}
	
	private List<Candidate> readcandidate(HttpServletRequest request) {
		//String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/candidateservice/readcandidate";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Candidate>> genericList = new GenericType<List<Candidate>>() {};
		
		List<Candidate> returnedList=b.get(genericList);
		return returnedList;
	}
	
	private List<Candidate> updatecandidate(HttpServletRequest request) {
		//A Candidate object to send to our web-service 
		Candidate q=new Candidate(request.getParameter("id"),request.getParameter("firstname"), request.getParameter("surname"));
		System.out.println(q);
		String uri = "http://127.0.0.1:8080/rest/candidateservice/updatecandidate";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a Candidate object as JSON string format
		Entity<Candidate> e=Entity.entity(q,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Candidate>> genericList = new GenericType<List<Candidate>>() {};
		
		//Posting data (Entity<ArrayList<Candidate>> e) to the given address
		List<Candidate> returnedList=b.put(e, genericList);
		return returnedList;
	}
	
	private List<Candidate> deletecandidate(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/candidateservice/deletecandidate/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Candidate>> genericList = new GenericType<List<Candidate>>() {};
		
		//Posting data (Entity<ArrayList<Candidate>> e) to the given address
		List<Candidate> returnedList=b.delete(genericList);
		return returnedList;
	}
}