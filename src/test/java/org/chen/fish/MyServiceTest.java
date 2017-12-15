package org.chen.fish;

import org.chen.fish.dao.CityMapper;
import org.chen.fish.entity.City;
import org.chen.fish.service.MyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MyServiceTest {

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private MyService myService ;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testFind(){
        City city = new City();
        city.setId(1L);
        city.setName("sh");
        Mockito.when(cityMapper.findByState("cd","sc")).thenReturn(city);
        myService.find("cd","sc");
    }
}
