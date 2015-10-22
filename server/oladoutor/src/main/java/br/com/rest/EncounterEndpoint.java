package br.com.rest;

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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.com.model.Encounter;

@Stateless
@Path("/encounters")
public class EncounterEndpoint {
	@PersistenceContext(unitName = "oladoutor-pu")
	private EntityManager em;

	@POST
	@Consumes("application/json")
	@Path("/create")
	public Response create(Encounter entity) {
		em.persist(entity);
		
		return Response.
				created(UriBuilder.
						fromResource(EncounterEndpoint.class)
						.path(String.valueOf(entity.getId())).
						build()).
				build();
	}

	@DELETE
	@Path("/delete/last/{patientId:[0-9][0-9]*}")
	public Response deleteLastByPatientId(@PathParam("patientId") Integer patientId) {
		TypedQuery<Encounter> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT e FROM Encounter e WHERE e.patient = :patientId ORDER BY e.date",
						Encounter.class);
		
		findByIdQuery.setParameter("entityId", patientId);
		
		Encounter entity;
		
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		em.remove(entity);
		
		return Response.noContent().build();
	}

	@GET
	@Path("/find/last/{patient:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findLastByPatientId(@PathParam("patient") Integer patientId) {
		TypedQuery<Encounter> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT e FROM Encounter e WHERE e.patient = :patientId ORDER BY e.date",
						Encounter.class);
		
		findByIdQuery.setParameter("entityId", patientId);
		
		Encounter entity;
		
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

}
