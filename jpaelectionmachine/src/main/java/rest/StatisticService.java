package rest;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Statistic;

@Path("/statisticservice")
public class StatisticService {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaelectionmachine");// name of project which is mentioned in persistence
	@GET
	@Path("/readstatistics")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Statistic> readStatistic() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Statistic> list=em.createQuery("select a from Statistic a").getResultList();		
		em.getTransaction().commit();
		return list;
	}	
	@POST
	@Path("/addstatistics")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Statistic> addStatistics(Statistic statistic) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(statistic);//The actual insertion line
		em.getTransaction().commit();
		//Calling the method readStatistic() of this service
		List<Statistic> list=readStatistic();		
		return list;
	}	
	
}
