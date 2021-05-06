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

import data.Candidate;

@Path("/candidateservice")
public class CandidateService {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaelectionmachine");// name of project which is mentioned in persistence
	@GET
	@Path("/readcandidate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Candidate> readCandidate() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Candidate> list=em.createQuery("select a from Candidate a").getResultList();		
		em.getTransaction().commit();
		return list;
	}	
	@POST
	@Path("/addcandidate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Candidate> addCandidate(Candidate candidate) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(candidate);//The actual insertion line
		em.getTransaction().commit();
		//Calling the method readCandidate() of this service
		List<Candidate> list=readCandidate();		
		return list;
	}	
	@PUT
	@Path("/updatecandidate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Candidate> updateCandidate(Candidate candidate) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Candidate q=em.find(Candidate.class, candidate.getId());
		if (q!=null) {
			em.merge(candidate);//The actual update line
		}
		em.getTransaction().commit();
		//Calling the method readCandidate() of this service
		List<Candidate> list=readCandidate();		
		return list;
	}	
	@DELETE
	@Path("/deletecandidate/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Candidate> deleteCandidate(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Candidate q=em.find(Candidate.class, id);
		if (q!=null) {
			em.remove(q);//The actual insertion line
		}
		em.getTransaction().commit();
		//Calling the method readCandidate() of this service
		List<Candidate> list=readCandidate();		
		return list;
	}	
	@GET
	@Path("/readtoupdatecandidate/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Candidate readToUpdateCandidate(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Candidate q=em.find(Candidate.class, id);
		em.getTransaction().commit();
		return q;
	}	
}
