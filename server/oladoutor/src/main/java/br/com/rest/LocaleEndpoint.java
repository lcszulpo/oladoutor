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

import br.com.model.Locale;

@Stateless
@Path("/locales")
public class LocaleEndpoint {
	
	@PersistenceContext(unitName = "oladoutor-pu")
	private EntityManager em;

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Locale entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		if (entity.getDescription() == null || entity.getDescription().isEmpty()) {
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
		
		Locale entity = em.find(Locale.class, id);
		
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		em.remove(entity);
		
		return Response.ok().entity(String.valueOf(id)).build();
	}

	@GET
	@Produces("application/json")
	@Path("/list/actives")
	public List<Locale> listActives() {
		TypedQuery<Locale> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT l FROM Locale l WHERE l.status = :status ORDER BY l.id",
						Locale.class);
		
		findAllQuery.setParameter("status", Locale.Status.ACTIVE);
		
		final List<Locale> results = findAllQuery.getResultList();
		
		return results;
	}
	
	@GET
	@Produces("application/json")
	@Path("/list")
	public List<Locale> list() {
		TypedQuery<Locale> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT l FROM Locale l ORDER BY l.id",
						Locale.class);
		
		final List<Locale> results = findAllQuery.getResultList();
		
		return results;
	}
	
}
