package be.pxl.travelapi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:53403")
public class UserController {

    @Value("${keycloak.auth-server-url}")
    private static String SERVER_URL;

    @Value("${keycloak.realm}")
    private static String REALM;


    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

}
