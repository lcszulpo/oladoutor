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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.model.Encounter;

@Stateless
@Path("/encounters")
public class EncounterEndpoint {
	@PersistenceContext(unitName = "oladoutor-pu")
	private EntityManager em;

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Encounter entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		entity = em.merge(entity);
		
		em.flush();
		
		return Response.ok().entity(entity).build();
	}

	@DELETE
	@Path("/delete/last/{patientId:[0-9][0-9]*}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteLastByPatientId(@PathParam("patientId") Integer patientId) {
		TypedQuery<Encounter> findByIdQuery = em.
				createQuery(
					"SELECT DISTINCT e FROM Encounter e WHERE e.patient.id = :patientId ORDER BY e.date",
					Encounter.class).
				setMaxResults(1);
		
		findByIdQuery.setParameter("patientId", patientId);
		
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
		
		return Response.ok().entity(String.valueOf(patientId)).build();
	}

	@GET
	@Path("/find/last/{patientId:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findLastByPatientId(@PathParam("patientId") Integer patientId) {
		TypedQuery<Encounter> findByIdQuery = em.
				createQuery(
					"SELECT DISTINCT e FROM Encounter e WHERE e.patient.id = :patientId ORDER BY e.date",
					Encounter.class).
				setMaxResults(1);
		
		findByIdQuery.setParameter("patientId", patientId);
		
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
