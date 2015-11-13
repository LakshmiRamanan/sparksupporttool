package com.sparksupporttool.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sparksupporttool.beans.QueryBean;
import com.sparksupporttool.beans.ResultBean;
import com.sparksupporttool.beans.ResultJsonBean;
import com.sparksupporttool.service.CassandraSparkService;

@Component
@Path("/query")
@Produces(MediaType.APPLICATION_JSON)
public class CassandraSparkResource {

	@Autowired
	CassandraSparkService cassandraSparkService;

	@GET
	@Path("/balance")
	public Response getAccountBalanceByRealmId(
			@QueryParam(value = "realmId") String realmId) {
		System.out.println("RealmId : " + realmId);
		ResultBean result = cassandraSparkService
				.getAccountBalanceByRealmId(realmId);
		return Response.status(200).entity(result).build();

	}

	@GET
	public Response getHey() {
		return Response.status(200).entity("Hey").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getQueryResults(QueryBean query) {
		ResultJsonBean result = cassandraSparkService
				.getQueryResultsAsJson(query);
		return Response.status(200).entity(result).build();
	}

}
