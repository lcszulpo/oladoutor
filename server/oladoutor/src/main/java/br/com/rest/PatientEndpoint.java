package br.com.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.com.model.Patient;

@Stateless
@Path("/patients")
public class PatientEndpoint {
	@PersistenceContext(unitName = "oladoutor-pu")
	private EntityManager em;

	@POST
	@Consumes("application/json")
	@Path("/create")
	public Response create(Patient entity) {
		em.persist(entity);
		
		return Response.
				created(UriBuilder.
						fromResource(PatientEndpoint.class).
						path(String.valueOf(entity.getId())).
						build())
				.build();
	}

	@DELETE
	@Path("/delete/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Integer id) {
		Patient entity = em.find(Patient.class, id);
		
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		em.remove(entity);
		
		return Response.noContent().build();
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

	@PUT
	@Path("/update/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Integer id, Patient entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if (!id.equals(entity.getId())) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		
		if (em.find(Patient.class, id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		try {
			entity = em.merge(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
	
	@PUT
	@Path("/update/status/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response updateStatus(@PathParam("id") Integer id) {
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		Patient entity = em.find(Patient.class, id);
		
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		try {
			
			if(entity.getStatus().equals(Patient.Status.ACTIVE)) {
				entity.setStatus(Patient.Status.INACTIVE);
			} else if(entity.getStatus().equals(Patient.Status.INACTIVE)) {
				entity.setStatus(Patient.Status.ACTIVE);
			} else {
				return Response.status(Status.BAD_REQUEST).build();
			}
			
			entity = em.merge(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}

}
