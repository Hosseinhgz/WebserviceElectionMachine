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
import data.Statistic;

@WebServlet(urlPatterns = {"/calcstatistics","/readtatistics"})
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
	  List<Statistic> list=null;
	  switch (action) {
	  case "/calcstatistics":
		  list=calcstatistics(request);break;

	  case "/readtatistics":
		  list=readstatistics(request);

	  }
	  request.setAttribute("statisticlist", list);
	  RequestDispatcher rd=request.getRequestDispatcher("./jsp/statistic/statistic.jsp");

	  rd.forward(request, response);
  }

	private List<Statistic> calcstatistics(HttpServletRequest request) {

		String uri = "http://127.0.0.1:8080/rest/questionservice/readquestion";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};		
		List<Question> qalist=b.get(genericList);
		
		ArrayList<Statistic> list=new ArrayList<Statistic>();
		int numAns1 = 0;
		int numAns2 = 0;
		int numAns3 = 0;
		int numAns4 = 0;
		int numAns5 = 0;

		for(int i=1; i<=qalist.size() ;i++) {
			if (qalist.get(i-1).getAnswer()==1) {
				numAns1++;
			}else if (qalist.get(i-1).getAnswer()==2) {
				numAns2++;

			}else if (qalist.get(i-1).getAnswer()==3) {
				numAns3++;

			}else if (qalist.get(i-1).getAnswer()==4) {
				numAns4++;

			}else if (qalist.get(i-1).getAnswer()==5) {
				numAns5++;
			}
		}
			int numAns1 = sdao.readStatisticNum(i,1);
			int numAns2 = sdao.readStatisticNum(i,2);
			int numAns3 = sdao.readStatisticNum(i,3);
			int numAns4 = sdao.readStatisticNum(i,4);
			int numAns5 = sdao.readStatisticNum(i,5);
			Statistic s = new Statistic(i, numAns1, numAns2, numAns3, numAns4, numAns5);
			list.add(s);				
		}
		
		
		Statistic st = new Statistic();
		String uri2 = "http://127.0.0.1:8080/rest/statisticservice/addstatistics";
		Client c2=ClientBuilder.newClient();
		WebTarget wt2=c2.target(uri2);
		Builder b2=wt2.request();
		//Here we create an Entity of a Statistic object as JSON string format
		Entity<Statistic> e2=Entity.entity(st,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Statistic>> genericList2 = new GenericType<List<Statistic>>() {};
		
		//Posting data (Entity<ArrayList<Statistic>> e) to the given address
		List<Statistic> returnedList2=b2.post(e2, genericList2);
		return returnedList2;
	}
	
	private List<Statistic> readstatistics(HttpServletRequest request) {
		String uri = "http://127.0.0.1:8080/rest/statisticservice/readstatistics";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Statistic>> genericList = new GenericType<List<Statistic>>() {};
		
		List<Statistic> returnedList=b.get(genericList);
		return returnedList;
	}

}