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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import data.Question;
import data.Statistic;
import model.Candidateanswer;

@WebServlet(urlPatterns = {"/calcstatistics"})
public class HandleStatistics extends HttpServlet {

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
	  List<Candidateanswer> calist=null;
	  List<Statistic> stlist= new ArrayList<Statistic>();

	  switch (action) {
	  case "/calcstatistics":
		  qalist=readquestion(request);
		  calist=readcandidateanswer(request);
		  
		  

			int numAns1 = 0;
			int numAns2 = 0;
			int numAns3 = 0;
			int numAns4 = 0;
			int numAns5 = 0;

			for(int i=1; i<=qalist.size() ;i++) {
				for (int j=0; j<calist.size() ;j++) {
					if (calist.get(j).getCandidateans()==1 && calist.get(j).getQuestion()==i) {
						numAns1++;
					}else if (calist.get(j).getCandidateans()==2 && calist.get(j).getQuestion()==i) {
						numAns2++;
		
					}else if (calist.get(j).getCandidateans()==3 && calist.get(j).getQuestion()==i) {
						numAns3++;
		
					}else if (calist.get(j).getCandidateans()==4 && calist.get(j).getQuestion()==i) {
						numAns4++;
		
					}else if (calist.get(j).getCandidateans()==5 && calist.get(j).getQuestion()==i) {
						numAns5++;
					}
				}
				
				// create a statistic object for every question and add it to the list
				Statistic s = new Statistic(i, numAns1, numAns2, numAns3, numAns4, numAns5);
				stlist.add(s);			  
			} 
		  
  
		  
		  
		  break;

	  }
	  request.setAttribute("statisticlist", stlist);
	  RequestDispatcher rd=request.getRequestDispatcher("./jsp/statistics.jsp");

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

}
