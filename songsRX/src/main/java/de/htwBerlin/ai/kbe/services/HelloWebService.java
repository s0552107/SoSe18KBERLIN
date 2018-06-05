package de.htwBerlin.ai.kbe.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.servlet.ServletContainer;



@Path("/hello")
public class HelloWebService {

	@GET
	@Path("/{name}")
	@Produces(MediaType.TEXT_HTML)
	public Response getMsg(@PathParam("name") String name) {
		String output = "<html><title>Our first JAX-RX Web Service</title>"  + 
						"<body><h1>" +
						"Hello <span style='color: red;'>" + name + "</span>"
						+ "</h1></body></html>";
		return Response.status(Response.Status.OK).entity(output).build();
	}
}