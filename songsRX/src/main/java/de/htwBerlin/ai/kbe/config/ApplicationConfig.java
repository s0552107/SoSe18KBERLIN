package de.htwBerlin.ai.kbe.config;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("songsRX")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig(){
    	register(new DependencyBinder());
        packages("de.htwBerlin.ai.kbe.service");

    }
}