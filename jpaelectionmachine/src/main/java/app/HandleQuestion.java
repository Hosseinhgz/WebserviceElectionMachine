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

import data.Question;

@WebServlet(urlPatterns = {"/addquestion", "/deletequestion","/updatequestion","/readquestion","/readtoupdatequestion","/showquestion"})
public class HandleQuestion extends HttpServlet {

	  @Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
		  doGet(request, response);
	  }
	  
	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
	  String action = request.getServletPath();
	  List<Question> list=null;
	  switch (action) {
	  case "/addquestion":
		  list=addquestion(request);break;
	  case "/deletequestion":
		  list=deletequestion(request);break;
	  case "/updatequestion":
		  list=updatequestion(request);break;
	  case "/readquestion":
		  list=readquestion(request);break;
	  case "/showquestion":
		  list=readquestion(request);
		  request.setAttribute("questionlist", list);
		  RequestDispatcher rd2=request.getRequestDispatcher("./jsp/showquestion.jsp");
		  rd2.forward(request, response);
		  break;
	  case "/readtoupdatequestion":
		  Question q=readtoupdatequestion(request);
		  request.setAttribute("question", q);
		  RequestDispatcher rd=request.getRequestDispatcher("./jsp/questiontoupdateform.jsp");
		  rd.forward(request, response);
		  return;
	  }
	  request.setAttribute("questionlist", list);
	  RequestDispatcher rd=request.getRequestDispatcher("./jsp/questionform.jsp");

	  rd.forward(request, response);
  }

	private Question readtoupdatequestion(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/questionservice/readtoupdatequestion/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Question question=b.get(Question.class);
		return question;
	}

	private List<Question> addquestion(HttpServletRequest request) {
		//A Question object to send to our web-service 
		Question f=new Question(request.getParameter("question"));
		System.out.println(f);
		String uri = "http://127.0.0.1:8080/rest/questionservice/addquestion";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a Question object as JSON string format
		Entity<Question> e=Entity.entity(f,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
		
		//Posting data (Entity<ArrayList<Question>> e) to the given address
		List<Question> returnedList=b.post(e, genericList);
		return returnedList;
	}
	
	private List<Question> readquestion(HttpServletRequest request) {
		//String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/questionservice/readquestion";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
		
		List<Question> returnedList=b.get(genericList);
		return returnedList;
	}
	
	private List<Question> updatequestion(HttpServletRequest request) {
		//A Question object to send to our web-service 
		Question q=new Question(request.getParameter("id"), request.getParameter("question"));
		System.out.println(q);
		String uri = "http://127.0.0.1:8080/rest/questionservice/updatequestion";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a Question object as JSON string format
		Entity<Question> e=Entity.entity(q,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
		
		//Posting data (Entity<ArrayList<Question>> e) to the given address
		List<Question> returnedList=b.put(e, genericList);
		return returnedList;
	}
	
	private List<Question> deletequestion(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/questionservice/deletequestion/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
		
		//Posting data (Entity<ArrayList<Question>> e) to the given address
		List<Question> returnedList=b.delete(genericList);
		return returnedList;
	}
}