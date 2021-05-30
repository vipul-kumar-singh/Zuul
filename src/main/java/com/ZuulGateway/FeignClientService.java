package com.ZuulGateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "Zuulgateway")
public interface FeignClientService {
    @RequestMapping(value = "/userms/user", method = RequestMethod.POST)
    public void sendUserDetails(UserDetails userDetails);

    @RequestMapping(method = RequestMethod.GET, value = "/userms/hello")
    public String hello();

    @RequestMapping(method = RequestMethod.GET, value = "/usernotif/notif")
    public void notif();

    @RequestMapping(method = RequestMethod.GET, value = "/usernotif/success")
    public void success();
}