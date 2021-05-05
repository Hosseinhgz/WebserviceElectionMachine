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

import data.Answer;

@Path("/answerservice")
public class AnswerService {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaelectionmachine");// name of project which is mentioned in persistence
	@GET
	@Path("/readanswer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Answer> readAnswer() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Answer> list=em.createQuery("select a from Answer a").getResultList();		
		em.getTransaction().commit();
		return list;
	}	
	@POST
	@Path("/addanswer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Answer> addAnswer(Answer answer) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(answer);//The actual insertion line
		em.getTransaction().commit();
		//Calling the method readAnswer() of this service
		List<Answer> list=readAnswer();		
		return list;
	}	
	@PUT
	@Path("/updateanswer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Answer> updateAnswer(Answer answer) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Answer q=em.find(Answer.class, answer.getId());
		if (q!=null) {
			em.merge(answer);//The actual update line
		}
		em.getTransaction().commit();
		//Calling the method readAnswer() of this service
		List<Answer> list=readAnswer();		
		return list;
	}	
	@DELETE
	@Path("/deleteanswer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Answer> deleteAnswer(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Answer q=em.find(Answer.class, id);
		if (q!=null) {
			em.remove(q);//The actual insertion line
		}
		em.getTransaction().commit();
		//Calling the method readAnswer() of this service
		List<Answer> list=readAnswer();		
		return list;
	}	
	@GET
	@Path("/readtoupdateanswer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Answer readToUpdateAnswer(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Answer q=em.find(Answer.class, id);
		em.getTransaction().commit();
		return q;
	}	
}
