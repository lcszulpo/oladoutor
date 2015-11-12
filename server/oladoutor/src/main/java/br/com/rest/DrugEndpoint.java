package br.com.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

import br.com.model.Drug;

/**
 * 
 */
@Stateless
@Path("/drugs")
public class DrugEndpoint {
	
	@PersistenceContext(unitName = "oladoutor-pu")
	private EntityManager em;

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Drug entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		entity = em.merge(entity);
		
		em.flush();
		
		return Response.ok().entity(entity).build();
	}

	@DELETE
	@Path("/delete/{id:[0-9][0-9]*}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteById(@PathParam("id") Integer id) {
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		Drug entity = em.find(Drug.class, id);
		
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		em.remove(entity);
		
		return Response.ok().entity(String.valueOf(id)).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Integer id) {
		TypedQuery<Drug> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT d FROM Drug d WHERE d.id = :entityId ORDER BY d.id",
						Drug.class);
		findByIdQuery.setParameter("entityId", id);
		
		Drug entity;
		
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(entity).build();
	}

	@GET
	@Produces("application/json")
	@Path("/list")
	public List<Drug> list() {
		TypedQuery<Drug> findAllQuery = em.createQuery(
				"SELECT DISTINCT d FROM Drug d ORDER BY d.description", Drug.class);
		
		final List<Drug> results = findAllQuery.getResultList();
		
		return results;
	}
	
	@GET
	@Produces("application/json")
	@Path("/list/actives")
	public List<Drug> listActives() {
		TypedQuery<Drug> findAllQuery = em.createQuery(
				"SELECT DISTINCT d FROM Drug d WHERE d.status = :status ORDER BY d.description", Drug.class);
		
		findAllQuery.setParameter("status", Drug.Status.ACTIVE);
		
		final List<Drug> results = findAllQuery.getResultList();
		
		return results;
	}

}
