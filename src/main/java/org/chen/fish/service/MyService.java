package org.chen.fish.service;


import org.chen.fish.dao.CityMapper;
import org.chen.fish.entity.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private CityMapper cityMapper;

    private static final Logger logger = LoggerFactory.getLogger(MyService.class);

    public void find(String name,String state){
        City city = cityMapper.findByState(name, state);
        logger.info(city.toString());
    }
}
