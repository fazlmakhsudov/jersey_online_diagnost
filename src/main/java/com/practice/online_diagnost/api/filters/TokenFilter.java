//package com.practice.online_diagnost.api.filters;
//
//import com.practice.online_diagnost.services.TokenService;
//import com.practice.online_diagnost.services.TokenServiceImpl;
//
//import javax.annotation.Priority;
//import javax.ws.rs.Priorities;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.core.HttpHeaders;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.Provider;
//import java.util.Objects;
//
//@Provider
//@Secured
//@Priority(Priorities.AUTHENTICATION)
//public class TokenFilter implements ContainerRequestFilter {
//    private final String AUTHENTIFICATION_PREFIX = "Bearer ";
//    private final TokenService tokenService = TokenServiceImpl.getInstance();
//
//
//    @Override
//    public void filter(ContainerRequestContext requestContext) {
//        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//        boolean validated = false;
//        if (!Objects.isNull(authorizationHeader)) {
//            String token = authorizationHeader.substring(AUTHENTIFICATION_PREFIX.length()).trim();
//            validated = tokenService.validate(token);
//        }
//        if (!validated) {
//            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//        }
//    }
//}
