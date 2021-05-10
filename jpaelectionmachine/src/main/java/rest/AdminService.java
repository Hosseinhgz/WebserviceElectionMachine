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

import data.Admin;

@Path("/adminservice")
public class AdminService {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaelectionmachine");// name of project which is mentioned in persistence
	@GET
	@Path("/readadmin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Admin> readAdmin() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Admin> list=em.createQuery("select a from Admin a").getResultList();		
		em.getTransaction().commit();
		return list;
	}	
	@POST
	@Path("/addadmin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Admin> addAdmin(Admin admin) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(admin);//The actual insertion line
		em.getTransaction().commit();
		//Calling the method readAdmin() of this service
		List<Admin> list=readAdmin();		
		return list;
	}	
	@PUT
	@Path("/updateadmin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Admin> updateAdmin(Admin admin) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Admin q=em.find(Admin.class, admin.getUsername());
		if (q!=null) {
			em.merge(admin);//The actual update line
		}
		em.getTransaction().commit();
		//Calling the method readAdmin() of this service
		List<Admin> list=readAdmin();		
		return list;
	}	
	@DELETE
	@Path("/deleteadmin/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Admin> deleteAdmin(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Admin q=em.find(Admin.class, id);
		if (q!=null) {
			em.remove(q);//The actual insertion line
		}
		em.getTransaction().commit();
		//Calling the method readAdmin() of this service
		List<Admin> list=readAdmin();		
		return list;
	}	
	@GET
	@Path("/readtoupdateadmin/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Admin readToUpdateAdmin(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Admin q=em.find(Admin.class, id);
		em.getTransaction().commit();
		return q;
	}	
}
