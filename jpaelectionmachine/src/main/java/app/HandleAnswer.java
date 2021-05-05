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

import data.Answer;

@WebServlet(urlPatterns = {"/addanswer", "/deleteanswer","/updateanswer","/readanswer","/readtoupdateanswer","/saveanswers"})
public class HandleAnswer extends HttpServlet {

	  @Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
		  doGet(request, response);
	  }
	  
	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
	  String action = request.getServletPath();
	  List<Answer> list=null;
	  switch (action) {
	  case "/addanswer":
		  list=addanswer(request);break;
	  case "/deleteanswer":
		  String id=request.getParameter("id");
		  list=deleteanswer(request);break;
	  case "/updateanswer":
		  list=updateanswer(request);break;
	  case "/readanswer":
		  list=readanswer(request);break;
	  case "/saveanswer":
		  list=saveanswers(request);break;
	  case "/readtoupdateanswer":
		  Answer q=readtoupdateanswer(request);
		  request.setAttribute("answer", q);
		  RequestDispatcher rd=request.getRequestDispatcher("./jsp/answertoupdateform.jsp");
		  rd.forward(request, response);
		  return;
	  }
	  request.setAttribute("answerlist", list);
	  RequestDispatcher rd=request.getRequestDispatcher("./jsp/finalanswers.jsp");
	  rd.forward(request, response);
  }

	private Answer readtoupdateanswer(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/answerservice/readtoupdateanswer/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Answer answer=b.get(Answer.class);
		return answer;
	}

	private List<Answer> addanswer(HttpServletRequest request) {
		//A Answer object to send to our web-service 
		Answer f=new Answer(request.getParameter("answer"));
		System.out.println(f);
		String uri = "http://127.0.0.1:8080/rest/answerservice/addanswer";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a answer object as JSON string format
		Entity<Answer> e=Entity.entity(f,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Answer>> genericList = new GenericType<List<Answer>>() {};
		
		//Posting data (Entity<ArrayList<Answer>> e) to the given address
		List<Answer> returnedList=b.post(e, genericList);
		return returnedList;
	}
	
	private List<Answer> readanswer(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/answerservice/readanswer";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Answer>> genericList = new GenericType<List<Answer>>() {};
		
		List<Answer> returnedList=b.get(genericList);
		return returnedList;
	}
	
	private List<Answer> updateanswer(HttpServletRequest request) {
		//A Answer object to send to our web-service 
		Answer q=new Answer(request.getParameter("id"), request.getParameter("answer"));
		System.out.println(q);
		String uri = "http://127.0.0.1:8080/rest/answerservice/updateanswer";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a Answer object as JSON string format
		Entity<Answer> e=Entity.entity(q,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Answer>> genericList = new GenericType<List<Answer>>() {};
		
		//Posting data (Entity<ArrayList<Answer>> e) to the given address
		List<Answer> returnedList=b.put(e, genericList);
		return returnedList;
	}
	
	private List<Answer> saveanswers(HttpServletRequest request) {
		//A Answer object to send to our web-service 
		ArrayList<Answer> answers = new ArrayList<Answer>();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaelectionmachine");
		EntityManager em=emf.createEntityManager();
		for (int i = 0;i<=19;i++) {		
			Answer q=new Answer(request.getParameter(""+i));
			if(q.getAnswer()>0) {
				System.out.println(q);
				answers.add(q);
				em.getTransaction().begin();
				em.persist(q);
				em.getTransaction().commit();
				
			}else {
				Answer q1=new Answer(0);
				answers.add(q1);
				em.getTransaction().begin();
				em.persist(q1);
				em.getTransaction().commit();
			}
		}
		return answers;
	}
	
	private List<Answer> deleteanswer(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/answerservice/deleteanswer/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Answer>> genericList = new GenericType<List<Answer>>() {};
		
		//Posting data (Entity<ArrayList<Answer>> e) to the given address
		List<Answer> returnedList=b.delete(genericList);
		return returnedList;
	}
}