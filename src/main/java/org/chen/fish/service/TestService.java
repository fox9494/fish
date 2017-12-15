package org.chen.fish.service;

import org.chen.fish.dao.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {


    private String age;

    @Autowired
    private CityMapper cityMapper;


    public void test(){

    }
}
