//package com.practice.library.api.filters;
//
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerResponseContext;
//import javax.ws.rs.container.ContainerResponseFilter;
//import javax.ws.rs.ext.Provider;
//import java.io.IOException;
//
//import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
//import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
//
//@Provider
//public class CorsFilter implements ContainerResponseFilter {
//
//    @Override
//    public void filter(final ContainerRequestContext requestContext,
//                       final ContainerResponseContext cres) throws IOException {
//        cres.getHeaders().add("Access-Control-Allow-Origin", "*");
////        cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept");
//        String headers = CONTENT_TYPE + "," + AUTHORIZATION;
//        cres.getHeaders().add("Access-Control-Allow-Headers", headers);
////        cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
//        cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
////        cres.getHeaders().add("Access-Control-Max-Age", "1209600");
//    }
//
//}