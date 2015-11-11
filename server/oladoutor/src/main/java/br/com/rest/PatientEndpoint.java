package br.com.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.model.Patient;

@Stateless
@Path("/patients")
public class PatientEndpoint {
	
	@PersistenceContext(unitName = "oladoutor-pu")
	private EntityManager em;

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Patient entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if (entity.getName() == null || entity.getName().isEmpty()) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if (entity.getLastName() == null || entity.getLastName().isEmpty()) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if (entity.getAge() == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if (entity.getAgeType() == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if (entity.getSex() == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if (entity.getLocale() == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if (entity.getStatus() == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		entity = em.merge(entity);
		
		em.flush();
		
		return Response.ok().entity(entity).build();
	}

	@DELETE
	@Path("/delete/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Integer id) {
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		Patient entity = em.find(Patient.class, id);
		
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		em.remove(entity);
		
		return Response.ok().entity(id).build();
	}

	@GET
	@Produces("application/json")
	@Path("/list/actives")
	public List<Patient> listActives() {
		TypedQuery<Patient> findAllQuery = em
				.createQuery("SELECT DISTINCT p FROM Patient p LEFT JOIN FETCH p.locale WHERE p.status = :status ORDER BY p.id", Patient.class);

		findAllQuery.setParameter("status", Patient.Status.ACTIVE);
		
		final List<Patient> results = findAllQuery.getResultList();

		return results;
	}
	
	@GET
	@Produces("application/json")
	@Path("/list/inactives")
	public List<Patient> listInactives() {
		TypedQuery<Patient> findAllQuery = em
				.createQuery("SELECT DISTINCT p FROM Patient p LEFT JOIN FETCH p.locale WHERE p.status = :status ORDER BY p.id", Patient.class);

		findAllQuery.setParameter("status", Patient.Status.INACTIVE);
		
		final List<Patient> results = findAllQuery.getResultList();

		return results;
	}

}
