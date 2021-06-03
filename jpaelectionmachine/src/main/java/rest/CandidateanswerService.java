package rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Candidateanswer;

@Path("/candidateanswerservice")
public class CandidateanswerService {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaelectionmachine");// name of project which is mentioned in persistence
	@GET
	@Path("/readcandidateanswer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Candidateanswer> readCandidateanswer() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Candidateanswer> list=em.createQuery("select a from Candidateanswer a").getResultList();		
		em.getTransaction().commit();
		return list;
	}	
	@POST
	@Path("/addcandidateanswer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Candidateanswer> addCandidateanswer(Candidateanswer candidateanswer) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(candidateanswer);//The actual insertion line
		em.getTransaction().commit();
		//Calling the method readCandidateanswer() of this service
		List<Candidateanswer> list=readCandidateanswer();		
		return list;
	}	
	@PUT
	@Path("/updatecandidateanswer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Candidateanswer> updateCandidateanswer(Candidateanswer candidateanswer) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Candidateanswer q=em.find(Candidateanswer.class, candidateanswer.getId());
		if (q!=null) {
			em.merge(candidateanswer);//The actual update line
		}
		em.getTransaction().commit();
		//Calling the method readCandidateanswer() of this service
		List<Candidateanswer> list=readCandidateanswer();		
		return list;
	}	
	@DELETE
	@Path("/deletecandidateanswer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Candidateanswer> deleteCandidateanswer(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Candidateanswer q=em.find(Candidateanswer.class, id);
		if (q!=null) {
			em.remove(q);//The actual insertion line
		}
		em.getTransaction().commit();
		//Calling the method readCandidateanswer() of this service
		List<Candidateanswer> list=readCandidateanswer();		
		return list;
	}	
	@GET
	@Path("/readtoupdatecandidateanswer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Candidateanswer readToUpdateCandidateanswer(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Candidateanswer q=em.find(Candidateanswer.class, id);
		em.getTransaction().commit();
		return q;
	}	
}
