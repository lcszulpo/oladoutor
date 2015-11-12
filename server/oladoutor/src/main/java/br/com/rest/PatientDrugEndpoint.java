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

import br.com.model.PatientDrug;

/**
 * 
 */
@Stateless
@Path("/patientdrugs")
public class PatientDrugEndpoint {
	@PersistenceContext(unitName = "oladoutor-pu")
	private EntityManager em;

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(PatientDrug entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		entity = em.merge(entity);
		
		em.flush();
		
		return Response.ok().entity(entity).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteById(@PathParam("id") Integer id) {
		PatientDrug entity = em.find(PatientDrug.class, id);
		
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		em.remove(entity);
		
		return Response.ok().entity(String.valueOf(id)).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Integer id) {
		TypedQuery<PatientDrug> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT p FROM PatientDrug p WHERE p.id = :entityId ORDER BY p.id",
						PatientDrug.class);
		
		findByIdQuery.setParameter("entityId", id);
		
		PatientDrug entity;
		
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
	@Path("/list/{idPatient:[0-9][0-9]*}")
	@Produces("application/json")
	public List<PatientDrug> listByPatient(@PathParam("idPatient") Integer idPatient) {
		TypedQuery<PatientDrug> findAllQuery = em.createQuery(
				"SELECT DISTINCT p FROM PatientDrug p WHERE p.patient.id = :idPatient ORDER BY p.time",
				PatientDrug.class);
		
		findAllQuery.setParameter("idPatient", idPatient);
		
		final List<PatientDrug> results = findAllQuery.getResultList();
		
		return results;
	}

}
