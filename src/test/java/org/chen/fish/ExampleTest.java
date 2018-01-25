package org.chen.fish;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.mockito.Mockito.*;
public class ExampleTest {

    @Mock   //该注解也是mock一个对象,但是必须的调用MockitoAnnotations.initMocks(this)方法
    private ServiceHelp serviceHelp;

    //@InjectMocks 自动注入mock注解对象

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testExample(){
        List list = mock(List.class);
        list.add("one");
        list.clear();
        verify(list).add("one");//检查mock的add方法是否调用
        verify(list).clear();
//        verify(list).get(0);
    }

    @Test
    public void testMethod2(){
        List list = mock(List.class);
        when(list.get(anyInt())).thenReturn("any");
        when(list.get(0)).thenReturn("hello");
        System.out.println(list.get(0));
        System.out.println(list.get(99));
    }

    @Test
    public void testMethod3(){
        ServiceHelp mockHelp = mock(ServiceHelp.class);
        //mock的参数带matcher的话，所有参数都需要带
        when(mockHelp.getSome(eq("my"),anyString())).thenReturn("chenlile");
        System.out.println(mockHelp.getSome("my","hello"));
    }

    @Test
    public void testMethod4(){
        //mock的参数带matcher的话，所有参数都需要带
        when(serviceHelp.getSome("my","name")).thenReturn("chenzhijia");
        System.out.println(serviceHelp.getSome("my","name"));
    }

    @Test
    public void testMethod5(){
        //mock的参数带matcher的话，所有参数都需要带
        when(serviceHelp.getSome("my","name")).thenThrow(new RuntimeException()).thenReturn("chenzhijia");
//        serviceHelp.getSome("my","name");
        serviceHelp.getSome("my","abc");
//        System.out.println(serviceHelp.getSome("my","name"));
//        System.out.println(serviceHelp.getSome("my","name"));
    }

    @Test
    public void testMethod6(){
        //mock的参数带matcher的话，所有参数都需要带
        when(serviceHelp.getSome("my","name")).thenReturn("chenzhijia","chenille");
        System.out.println(serviceHelp.getSome("my","name"));
        System.out.println(serviceHelp.getSome("my","name"));
    }
}
