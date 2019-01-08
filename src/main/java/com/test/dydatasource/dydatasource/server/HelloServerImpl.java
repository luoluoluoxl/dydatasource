package com.test.dydatasource.dydatasource.server;

import com.test.dydatasource.dydatasource.mapper.HelloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 罗项令
 * @DATE 2019/1/7  16:56.
 */
@SuppressWarnings("ALL")
@Service
public class HelloServerImpl implements HelloServer{
    @Autowired
    HelloMapper helloMapper;

    @Override
    public List hello(String s) {
        return helloMapper.selectAll();
    }


}
