package com.weasley.web;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;

import com.weasley.data.Customer;
import com.weasley.data.CustomerDAO;

// http://localhost:8080/DemoJEE/rest/customers
@Path("/customers")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CustomerREST {
	private CustomerDAO dao = new CustomerDAO();

	// http://localhost:8080/DemoJEE/rest/customers
	@GET
	public List<Customer> findAll() {
		return dao.findAll();
	}
	
	// http://localhost:8080/DemoJEE/rest/customers/1234
	@GET
	@Path("{cid: \\d+}")
	public Customer findById(@PathParam ("cid") Long customerId) {
		return dao.findById(customerId);
	}

	// http://localhost:8080/DemoJEE/rest/customers/Weasley
	@GET
	@Path("{lastName}")
	public List<Customer> findByLastName(@PathParam("lastName") String lastName) {
		return dao.findByLastName(lastName);
	}

	@POST
	public Customer insert(Customer customer) {
		return dao.insert(customer);
	}

	// http://localhost:8080/DemoJEE/rest/customers
	@DELETE
	public Customer delete(Customer customer) {
		return dao.delete(customer);
	}
	
	// http://localhost:8080/DemoJEE/rest/customers/1234
	@DELETE
	@Path("{id: \\d+}")
	public Customer delete(@PathParam ("id") Long customerId) throws NoContentException {
		Customer customer = dao.findById(customerId);
		if (customer == null) {
			throw new javax.ws.rs.NotFoundException();
//			throw new javax.ws.rs.NotFoundException(Response.status(Response.Status.NOT_FOUND).build());
		}
		return dao.delete(customer);
	}

	@PUT
	public Customer update(Customer customer) {
		return dao.update(customer);
	}
	
	

}
