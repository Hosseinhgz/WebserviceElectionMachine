package app;

import java.io.*;
import java.util.*;

import java.math.BigInteger;  

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
import model.Result;
import model.Candidateanswer;
import data.Question;

@WebServlet(urlPatterns = {"/addanswer", "/deleteanswer","/updateanswer","/readanswer","/saveanswers",
		"/readonequestion","/backonequestion","/anscalculator"})
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
	  case "/anscalculator":
		  List<Question> qalist=null;
		  List<Candidateanswer> calist=null;
		  double[] resultarray = new double[6];
		  List<Result> resultlist=null;

		  
		  
		  double res = 0;
		  qalist=readcustomeranswers(request);
		  calist=readcandidateanswers(request);
		  int l = 0; 
		  // calculation for find the best candidates are here************************CALCULATIONS
		  for(int k = 0; k < 6; k++) {

			  for(int i = 0; i < 6*19; i++) // i max 19 * 6
			  {
				  for(int j = 0; j < 19; i++) { // j max = 19
					  if(qalist.get(j).getAnswer()==0 && calist.get(i).getCandidateid()==k) {
						  res=res + (1-((qalist.get(j).getAnswer()-calist.get(j).getCandidateans())*0.25));
						  l++;
						  
					  }
					  double percentresult = (res/l)*100;
					  resultarray[0] = percentresult;
					  
				  }
			  } 
		  }
		  Result r1 = new Result(1,resultarray[0]);
		  Result r2 = new Result(1,resultarray[0]);
		  Result r3 = new Result(1,resultarray[0]);
		  Result r4 = new Result(1,resultarray[0]);
		  Result r5 = new Result(1,resultarray[0]);
		  Result r6 = new Result(1,resultarray[0]);
		  resultlist.add(r1);
		  resultlist.add(r2);
		  resultlist.add(r3);
		  resultlist.add(r4);
		  resultlist.add(r5);
		  resultlist.add(r6);


		  request.setAttribute("result", resultlist);
		  RequestDispatcher rdc3=request.getRequestDispatcher("./jsp/result.jsp");
		  rdc3.forward(request, response);
		  
//		  request.setAttribute("candidateanswerlist", calist);
//		  RequestDispatcher rdc2=request.getRequestDispatcher("./jsp/result.jsp");
//		  rdc2.forward(request, response);
//		  request.setAttribute("answerlist", aqlist);
//		  RequestDispatcher rdc=request.getRequestDispatcher("./jsp/result.jsp");
//		  rdc.forward(request, response);

		  return;	  
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
	  
	// result/candidate/answers ****************************************************************************************
	// this function is getting data (candidate answers and customer answer from database and do the calculation
	// this function is returning the result for comparison	
	  private List<Candidateanswer> readcandidateanswers(HttpServletRequest request) {			
			// bring the question and customer answer to resault page
			String uri = "http://127.0.0.1:8080/rest/answerservice/readcandidateanswers";
			Client c=ClientBuilder.newClient();
			WebTarget wt=c.target(uri);
			Builder b=wt.request();
			//Create a GenericType to be able to get List of objects
			//This will be the second parameter of post method
			GenericType<List<Candidateanswer>> genericList = new GenericType<List<Candidateanswer>>() {};		
			List<Candidateanswer> returnedList=b.get(genericList);
			
			// bring the candidate answers to resault page
			
			return returnedList;
	}

	// questions.jsp - next - button***********************************************************************NEXT
	// main usage in next button
	private Question readonequestion(HttpServletRequest request) {
		
		// first part of function
		// read specific question to show in question.jsp
		int id=Integer.parseInt(request.getParameter("id"));
		String qu=request.getParameter("question");
		String ans=request.getParameter("answer");
		String uri = "http://127.0.0.1:8080/rest/answerservice/readonequestion/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Question question=b.get(Question.class);
		
		// second part of function
		// try to save the answer to the database with specific id 
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

	// questions.jsp - previous - button***********************************************************************BACK
	// main usage in previous button
	private Question backonequestion(HttpServletRequest request) {
		
		// first part of function
		// read specific question to show in question.jsp
		int id=Integer.parseInt(request.getParameter("id"));
		String qu=request.getParameter("question");
		String ans=request.getParameter("answer");
		String uri = "http://127.0.0.1:8080/rest/answerservice/readonequestion/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Question question=b.get(Question.class);
		
		// second part of function
		// try to save the answer to the database with specific id 
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
	// result.jsp - customer - answers
	// this function is getting data (candidate answers and customer answer from database and do the calculation*******
	// this function is returning the result for comparison
	private List<Question> readcustomeranswers(HttpServletRequest request) {
		
		// bring the question and customer answer to resault page
		String uri = "http://127.0.0.1:8080/rest/answerservice/readanswer";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};		
		List<Question> returnedList=b.get(genericList);
		
		// bring the candidate answers to resault page
		
		return returnedList;
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