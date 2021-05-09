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

import model.Answer;
import data.Question;

@WebServlet(urlPatterns = {"/addanswer", "/deleteanswer","/updateanswer","/readanswer","/saveanswers","/readonequestion","/backonequestion"})
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
	  case "/readonequestion":
		  Question q=readonequestion(request);
		  request.setAttribute("question", q);
		  RequestDispatcher rd=request.getRequestDispatcher("./jsp/questions.jsp");
		  rd.forward(request, response);
		  return;
	  case "/backonequestion":
		  Question qb=backonequestion(request);
		  request.setAttribute("question", qb);
		  RequestDispatcher rdb=request.getRequestDispatcher("./jsp/questions.jsp");
		  rdb.forward(request, response);
		  return;
	  }
	  request.setAttribute("answerlist", list);
	  RequestDispatcher rd=request.getRequestDispatcher("./jsp/question/showquestion.jsp");
	  rd.forward(request, response);
  }
	
	  // main usage in next button***********************************************************************NEXT
	private Question readonequestion(HttpServletRequest request) {
		
		// first part of function
		// read specific question to show in question.jsp****************************************IMPORTANT
		int id=Integer.parseInt(request.getParameter("id"));
		String qu=request.getParameter("question");
		String ans=request.getParameter("answer");
		String uri = "http://127.0.0.1:8080/rest/answerservice/readonequestion/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Question question=b.get(Question.class);
		
		// second part of function
		// try to save the answer to the database with specific id ************************************IMPORTANT
		Question q2=new Question((id-1), qu, ans);
		System.out.println(q2);
		String uri2 = "http://127.0.0.1:8080/rest/answerservice/updateanswer";
		Client c2=ClientBuilder.newClient();
		WebTarget wt2=c2.target(uri2);
		Builder b2=wt2.request();
		//Here we create an Entity of a Answer object as JSON string format
		Entity<Question> e2=Entity.entity(q2,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
		
		//Posting data (Entity<ArrayList<Answer>> e) to the given address
		List<Question> returnedList=b2.put(e2, genericList);
		
		return question;
	}

	  // main usage in previous button***********************************************************************NEXT
	private Question backonequestion(HttpServletRequest request) {
		
		// first part of function
		// read specific question to show in question.jsp****************************************IMPORTANT
		int id=Integer.parseInt(request.getParameter("id"));
		String qu=request.getParameter("question");
		String ans=request.getParameter("answer");
		String uri = "http://127.0.0.1:8080/rest/answerservice/readonequestion/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Question question=b.get(Question.class);
		
		// second part of function
		// try to save the answer to the database with specific id ************************************IMPORTANT
		Question q2=new Question((id+1), qu, ans);
		System.out.println(q2);
		String uri2 = "http://127.0.0.1:8080/rest/answerservice/updateanswer";
		Client c2=ClientBuilder.newClient();
		WebTarget wt2=c2.target(uri2);
		Builder b2=wt2.request();
		//Here we create an Entity of a Answer object as JSON string format
		Entity<Question> e2=Entity.entity(q2,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
		
		//Posting data (Entity<ArrayList<Answer>> e) to the given address
		List<Question> returnedList=b2.put(e2, genericList);
		
		return question;
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
	
//	private List<Answer> saveanswerss(HttpServletRequest request) {
//		//A Answer object to send to our web-service 
//		ArrayList<Answer> answers = new ArrayList<Answer>();
//		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaelectionmachine");
//		EntityManager em=emf.createEntityManager();
//		for (int i = 0;i<=19;i++) {		
//			Answer q=new Answer(request.getParameter(""+i));
//			System.out.print(q.getAnswer());
//			if(q.getAnswer()>0) {
//				System.out.println(q);
//				answers.add(q);
//				em.getTransaction().begin();
//				em.persist(q);
//				em.getTransaction().commit();
//				
//			}else {
//				Answer q1=new Answer(0);
//				answers.add(q1);
//				em.getTransaction().begin();
//				em.persist(q1);
//				em.getTransaction().commit();
//			}
//		}
//		return answers;
//	}
	
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