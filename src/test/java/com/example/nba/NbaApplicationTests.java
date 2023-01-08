package com.example.nba;

import com.example.nba.model.Player;
import com.example.nba.payload.LoginRequest;
import com.example.nba.repository.PlayerRepository;
import com.example.nba.security.JwtTokenProvider;
import com.example.nba.service.PlayerService;
import com.example.nba.service.PlayerServiceImplement;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.restassured.RestAssured;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;

import org.easymock.Mock;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.SequenceGenerator;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;


@SpringBootTest

class NbaApplicationTests {




    @Value("${app.jwtSecret}")
    private String jwtSecret;
    JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
    //EasyMock.expect(mockDao.findAll()).andReturn(playerList);

    @Test
    public void whenRequestGet_ThenOk(){
        RestAssured.with().when().request("Get", "/swagger-ui/").then().statusCode(200);
    }
    @Test
    public void whenRequestReadPlayer_ThenUnauthorize(){
        RestAssured.with().when().auth().oauth2("fakeToken").request("Get", "/player/read/").then().statusCode(401);
    }

    @Test
    public void whenRequestReadPlayer_ThenOK(){

        //System.out.println(setUp());
        System.out.println(getToken("4"));
        RestAssured.with().when().auth().oauth2(getToken("4")).request("Get", "/player/read/").then().statusCode(200);
    }
    private String getToken(String Id){
        return Jwts.builder()
                .setSubject(Id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }
    @Test
    public void whenRequestReadPlayer_ThenForbidden(){

        //System.out.println(setUp());
        System.out.println(getToken("9"));
        RestAssured.with().when().auth().oauth2(getToken("9")).request("Get", "/player/read/").then().statusCode(403);
    }

}
