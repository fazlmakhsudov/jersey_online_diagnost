//package com.practice.online_diagnost.api.resources.v1;
//
//import com.practice.online_diagnost.api.models.UserRequestModel;
//import com.practice.online_diagnost.repositories.MySQLUserRepositoryImpl;
//import com.practice.online_diagnost.services.TokenService;
//import com.practice.online_diagnost.services.TokenServiceImpl;
//import com.practice.online_diagnost.services.UserService;
//import com.practice.online_diagnost.services.UserServiceImpl;
//import com.practice.online_diagnost.services.domains.UserDomain;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.util.Objects;
//
//import static javax.ws.rs.core.HttpHeaders.*;
//import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
//
//@Path("/user")
//public class UserResource {
//    private final UserService userService = new UserServiceImpl(new MySQLUserRepositoryImpl());
//    private final TokenService tokenService = TokenServiceImpl.getInstance();
//
//    @POST
//    @Path("/signin")
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response authenticateUser(UserRequestModel userModel) {
//        UserDomain foundUser = userService.find(userModel.getName());
//        Response response;
//        if (Objects.isNull(foundUser)) {
//            response = Response.status(Response.Status.NOT_FOUND).build();
//        } else if (!foundUser.getPassword().equals(userModel.getPassword())) {
//            response = Response.status(UNAUTHORIZED).build();
//        } else {
//            String token = tokenService.generate(userModel.getName());
//            response = Response.ok("Bearer " + token).header(AUTHORIZATION, "Bearer " + token)
//                    .header(CONTENT_TYPE, MediaType.APPLICATION_JSON).build();
//
//        }
//        return response;
//    }
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response authenticateUser() {
//
//           Response response = Response.ok().header(AUTHORIZATION, "Bearer authorization token").build();
//        return response;
//    }
//}
