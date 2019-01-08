package com.test.dydatasource.dydatasource.controller;

import com.test.dydatasource.dydatasource.server.HelloServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 罗项令
 * @DATE 2019/1/7  16:52.
 */
@RestController
public class TestController {
    @Autowired
    HelloServer helloServer;
    @RequestMapping("hello/{projectId}")
    Object hello(@PathVariable String projectId) {
        if(projectId==null) {
             projectId = "0001";
        }
        List hello = helloServer.hello(projectId);
        return hello;
    }
}
