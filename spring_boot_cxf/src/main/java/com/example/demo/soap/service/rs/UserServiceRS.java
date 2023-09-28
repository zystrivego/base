package com.example.demo.soap.service.rs;

/**
 * Created by mabo-pc on 2017/7/8.
 */

import com.example.demo.soap.vo.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Path(value = "/")
@Consumes(MediaType.APPLICATION_JSON)
public interface UserServiceRS {
    @GET
    @Path(value = "/{id}/info")
    @Produces({"application/json", "application/xml"})
    User findUserById(@PathParam("id") String id);

    @GET
    @Path(value = "/search")
    @Produces({"application/json", "application/xml"})
    User findUserByName(@QueryParam("name") String name);

    @GET
    @Path("/file/{id}")
    @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    Response getFile(@PathParam("id") Long id) throws IOException;
}
