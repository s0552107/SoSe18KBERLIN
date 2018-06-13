package de.htw.ai.kbe.songsRX;


import com.oracle.webservices.internal.api.message.ContentType;
import de.htwBerlin.ai.kbe.bean.User;
import de.htwBerlin.ai.kbe.services.SongsWebService;
import de.htwBerlin.ai.kbe.storage.Parser;
import  org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;


import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;


import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class UserWebServiceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(SongsWebService.class);
    }
/**
    @Test
    public void getTokenUserNotFound(){
        User user = new User.Builder(5, "speck")
                .firstName("Mister")
                .lastName("Speck").build();



        Response output = target("auth?userId=" + user.getUserId() )
                .request()
                .get(Entity.entity(@QueryPar));
        assertEquals("Should return status 204", 204, output.getStatus());
    }
    **/
}
