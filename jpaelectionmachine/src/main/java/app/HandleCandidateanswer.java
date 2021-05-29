package app;

import java.io.IOException;
import java.util.List;

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

import model.Candidateanswer;

@WebServlet(urlPatterns = {"/addcandidateanswer", "/deletecandidateanswer","/updatecandidateanswer","/readcandidateanswer","/readtoupdatecandidateanswer","/showcandidateanswer"})
public class HandleCandidateanswer extends HttpServlet {

	  @Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
		  doGet(request, response);
	  }
	  
	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
	  String action = request.getServletPath();
	  List<Candidateanswer> list=null;
	  switch (action) {
	  case "/addcandidateanswer":
		  list=addcandidateanswer(request);break;
	  case "/deletecandidateanswer":
		  list=deletecandidateanswer(request);break;
	  case "/updatecandidateanswer":
		  list=updatecandidateanswer(request);break;
	  case "/readcandidateanswer":
		  list=readcandidateanswer(request);break;
	  case "/showcandidateanswer":
		  list=readcandidateanswer(request);
		  request.setAttribute("candidateanswerlist", list);
		  RequestDispatcher rd2=request.getRequestDispatcher("./jsp/candidateanswer/showcandidateanswer.jsp");
		  rd2.forward(request, response);
		  return;
	  }
	  request.setAttribute("candidateanswerlist", list);
	  RequestDispatcher rd=request.getRequestDispatcher("./jsp/candidateanswer/candidateanswerform.jsp");

	  rd.forward(request, response);
  }



	private List<Candidateanswer> addcandidateanswer(HttpServletRequest request) {
		//A Candidateanswer object to send to our web-service 
		Candidateanswer f=new Candidateanswer(request.getParameter("candidateid"), request.getParameter("question"), request.getParameter("candidateanswer"));
		System.out.println(f);
		String uri = "http://127.0.0.1:8080/rest/candidateanswerservice/addcandidateanswer";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a Candidateanswer object as JSON string format
		Entity<Candidateanswer> e=Entity.entity(f,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Candidateanswer>> genericList = new GenericType<List<Candidateanswer>>() {};
		
		//Posting data (Entity<ArrayList<Candidateanswer>> e) to the given address
		List<Candidateanswer> returnedList=b.post(e, genericList);
		return returnedList;
	}
	
	private List<Candidateanswer> readcandidateanswer(HttpServletRequest request) {
		String uri = "http://127.0.0.1:8080/rest/candidateanswerservice/readcandidateanswer";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Candidateanswer>> genericList = new GenericType<List<Candidateanswer>>() {};
		
		List<Candidateanswer> returnedList = b.get(genericList);
		return returnedList;
	}
	
	private List<Candidateanswer> updatecandidateanswer(HttpServletRequest request) {
		//A Candidateanswer object to send to our web-service 
		Candidateanswer q=new Candidateanswer(request.getParameter("id"), request.getParameter("candidateid"), request.getParameter("question"), request.getParameter("candidateanswer"));
		System.out.println(q);
		String uri = "http://127.0.0.1:8080/rest/candidateanswerservice/updatecandidateanswer";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a Candidateanswer object as JSON string format
		Entity<Candidateanswer> e=Entity.entity(q,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Candidateanswer>> genericList = new GenericType<List<Candidateanswer>>() {};
		
		//Posting data (Entity<ArrayList<Candidateanswer>> e) to the given address
		List<Candidateanswer> returnedList=b.put(e, genericList);
		return returnedList;
	}
	
	private List<Candidateanswer> deletecandidateanswer(HttpServletRequest request) {
		String id=request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/candidateanswerservice/deletecandidateanswer/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Candidateanswer>> genericList = new GenericType<List<Candidateanswer>>() {};
		
		//Posting data (Entity<ArrayList<Candidateanswer>> e) to the given address
		List<Candidateanswer> returnedList=b.delete(genericList);
		return returnedList;
	}
}