package io.getarrays.userservice.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAutherizationFilter extends OncePerRequestFilter {

	public CustomAutherizationFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 if(request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/token/refresh") ) {
			 filterChain.doFilter(request, response);
		 }else {
			String authorizationHeader = request.getHeader(AUTHORIZATION);
			if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")) {
				try {
					String token = authorizationHeader.substring("Bearer ".length());
					Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
					JWTVerifier verifier = JWT.require(algorithm).build();
					DecodedJWT decodedJWT = verifier.verify(token);
					String username = decodedJWT.getSubject();
					String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
					Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
					stream(roles).forEach(role -> {
						authorities.add(new SimpleGrantedAuthority(role));
					});
					UsernamePasswordAuthenticationToken authenticationToken = 
							new UsernamePasswordAuthenticationToken(username,null, authorities);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					filterChain.doFilter(request, response);
				}catch (Exception exception) {
					logger.info(exception.getMessage());
					response.setHeader("error", exception.getMessage());
					response.setStatus(FORBIDDEN.value());
					Map<String, String> error = new HashMap<>();
					error.put("error_message", exception.getMessage());
					response.setContentType(APPLICATION_JSON_VALUE);
					new ObjectMapper().writeValue(response.getOutputStream(), error);
					
				}
				
			}else {
				filterChain.doFilter(request, response);
			}
		}
	}


}
