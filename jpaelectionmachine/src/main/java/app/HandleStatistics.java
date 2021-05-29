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
	  List<Statistic> list= new ArrayList<Statistic>();

	  switch (action) {
	  case "/calcstatistics":
		  
		  qalist=ReadQuestion(request);
		  calist=ReadCandidateAnswer(request);

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
				list.add(s);			
			};break;
		  
	  }
	  
	  request.setAttribute("statisticlist", list);
	  RequestDispatcher rd=request.getRequestDispatcher("../jsp/showtest.jsp");
	  rd.forward(request, response);
	  
  }

	private List<Question> ReadQuestion(HttpServletRequest request) {
		
		// ***************************************
		// read all questions and customer answers
		String uri = "http://127.0.0.1:8080/rest/questionservice/readquestion";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};		
		List<Question> qalist=b.get(genericList);
		return qalist;
	}
		
		private List<Candidateanswer> ReadCandidateAnswer(HttpServletRequest request) {

		// **************************************
		// read all Candidate answers
		String uri1 = "http://127.0.0.1:8080/rest/candidateanswerservice/readcandidateanswer";
		Client c1=ClientBuilder.newClient();
		WebTarget wt1=c1.target(uri1);
		Builder b1=wt1.request();
		GenericType<List<Candidateanswer>> genericList1 = new GenericType<List<Candidateanswer>>() {};		
		List<Candidateanswer> calist=b1.get(genericList1);
		return calist;
	}
		


}