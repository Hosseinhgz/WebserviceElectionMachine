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

import data.Question;

@Path("/questionservice")
public class QuestionService {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaelectionmachine");// maybe electionmachine
	@GET
	@Path("/readquestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Question> readQuestion() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Question> list=em.createQuery("select q from Question q").getResultList();		
		em.getTransaction().commit();
		return list;
	}	
	@POST
	@Path("/addquestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Question> addQuestion(Question question) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(question);//The actual insertion line
		em.getTransaction().commit();
		//Calling the method readQuestion() of this service
		List<Question> list=readQuestion();		
		return list;
	}	
	@PUT
	@Path("/updatequestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Question> updateQuestion(Question question) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Question q=em.find(Question.class, question.getId());
		if (q!=null) {
			em.merge(question);//The actual update line
		}
		em.getTransaction().commit();
		//Calling the method readQuestion() of this service
		List<Question> list=readQuestion();		
		return list;
	}	
	@DELETE
	@Path("/deletequestion/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Question> deleteQuestion(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Question q=em.find(Question.class, id);
		if (q!=null) {
			em.remove(q);//The actual insertion line
		}
		em.getTransaction().commit();
		//Calling the method readQuestion() of this service
		List<Question> list=readQuestion();		
		return list;
	}	
	@GET
	@Path("/readtoupdatequestion/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Question readToUpdateQuestion(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Question q=em.find(Question.class, id);
		em.getTransaction().commit();
		return q;
	}	
}
