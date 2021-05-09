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

import model.Answer;
import data.Question;

@Path("/answerservice")
public class AnswerService {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaelectionmachine");// name of project which is mentioned in persistence
	@GET
	@Path("/readanswer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Question> readAnswer() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Question> list=em.createQuery("select a from Answer a").getResultList();		
		em.getTransaction().commit();
		return list;
	}	
	
	@PUT
	@Path("/updateanswer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Question> updateAnswer(Question question) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Question q=em.find(Question.class, question.getId());
		if (q!=null) {
			em.merge(question);//The actual update line
		}
		em.getTransaction().commit();
		//Calling the method readAnswer() of this service
		List<Question> list=readAnswer();		
		return list;
	}	
	@DELETE
	@Path("/deleteanswer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Question> deleteAnswer(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Question q=em.find(Question.class, id);
		if (q!=null) {
			em.remove(q);//The actual insertion line
		}
		em.getTransaction().commit();
		//Calling the method readAnswer() of this service
		List<Question> list=readAnswer();		
		return list;
	}	
	@GET
	@Path("/readonequestion/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Question readToUpdateAnswer(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Question q=em.find(Question.class, id);
		em.getTransaction().commit();
		return q;
	}	
}
