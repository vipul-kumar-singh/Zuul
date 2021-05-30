package com.ZuulGateway;

import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

@RestController
public class SignupController {

    @Autowired
    private FeignClientService loadBalancer;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private Producer producer;

    @GetMapping("/")
    public String index() {
        return "Zuul Gateway is Running";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public void postUser(@RequestBody User user) throws RestClientException, IOException {

        UserDetails userDetails = new UserDetails();
        BeanUtils.copyProperties(user, userDetails);
        authRepository.save(user);

        Gson gson = new Gson();

        producer.produce(gson.toJson(userDetails));

//        try {
//            System.out.println("\n" + loadBalancer.hello() + "\n");
//            loadBalancer.sendUserDetails(userDetails);
//            loadBalancer.notif();
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
    }
}
