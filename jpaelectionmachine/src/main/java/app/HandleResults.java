package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import model.Candidate;
import model.Candidateanswer;
import data.Question;
import data.Statistic;
import data.UserId;
import data.Result;

@WebServlet(urlPatterns = {"/calcresults"})
public class HandleResults extends HttpServlet {

	  @Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
		  doGet(request, response);
	  }
	  
	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
	  String action = request.getServletPath();
	  List<Question> qalist=null;
	  List<Candidateanswer> allcalist=null;
	  List<Candidate> candidatelist=null;

	  ArrayList<data.Result> resultlist= new ArrayList<>();

	  switch (action) {
	  case "/calcresults":
		  qalist=readquestion(request);
		  allcalist=readcandidateanswer(request);
		  candidatelist=readcandidate(request);

			for(int j=1; j<=10;j++) {	
				ArrayList<Candidateanswer> caAnslist=new ArrayList<Candidateanswer>();

				for(int i=0; i<allcalist.size() ;i++) {
					// calculations are here *********** IMPORTANT *************
					if (allcalist.get(i).getCandidateid()==j) {
						caAnslist.add(allcalist.get(i));
					}
				}
				double res = 0;
				double percentResult = 0;
				int z=0;

				for(int i=0;i<qalist.size();i++)	{
					int x = caAnslist.get(i).getCandidateans();
					int y = qalist.get(i).getAnswer();
					//System.out.println("i loop");
					if (y!=0) {
						res = res + (1-(java.lang.Math.abs(x-y)*0.25));
						z++;
					}
				
				}
				percentResult = res*100/z;
				data.Result r = new data.Result(j,UserId.getUserId(),percentResult);
				resultlist.add(r);
				//System.out.println("j loop");

			}
		  	  
		  break;

	  }


	  request.setAttribute("resultlist", resultlist);
	  RequestDispatcher rd=request.getRequestDispatcher("./jsp/suggestions.jsp");

	  rd.forward(request, response);
  }

		private List<Question> readquestion(HttpServletRequest request) {
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
		private List<Candidate> readcandidate(HttpServletRequest request) {
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
}
