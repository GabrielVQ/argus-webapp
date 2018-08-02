package cl.argus.services;

import cl.argus.models.User;
import cl.argus.repositories.UserRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;



@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserService {
    @Autowired
    UserRepository UserRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User create(@RequestBody User resource){
        return UserRepository.save(resource);
    }
	
	@RequestMapping(method = RequestMethod.POST,value="/validate")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User validar(@RequestBody HashMap<String,String> resource){
    	String correo = resource.get("email");
    	String pass = resource.get("password");
    	User v = UserRepository.findByEmailAndPassword(correo,pass);
    	return v;
    }
    


    @RequestMapping(method = RequestMethod.POST, value= "/auth")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public boolean Auth(@RequestBody HashMap<String,String> resource) throws IOException {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {

                    public java.security.cert.X509Certificate[] getAcceptedIssuers()
                    {
                        return null;
                    }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                        //No need to implement.
                    }
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                        //No need to implement.
                    }
                }
        };


        
        return true;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/validate/{email}")
    @ResponseBody
    public User getUser(@PathVariable("email") String email){
        System.out.println(UserRepository.findByEmail(email));
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public User getUserId(@PathVariable("id") Long id){
        return (UserRepository.findOne(id));
    }



}