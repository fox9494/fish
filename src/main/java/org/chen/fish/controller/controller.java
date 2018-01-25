package org.chen.fish.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.chen.fish.entity.Job;
import org.chen.fish.service.MyService;
import org.chen.fish.utils.AESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by chenlile on 17-3-15.
 */

//结合了 @ResponseBody 与 @Controller 注解的功能
//表示这个controller提供json，xml或其他自定义的内容媒体返回
@RestController
public class controller {


    private static Logger logger= LoggerFactory.getLogger(controller.class);

    private MyService myService;



    @ApiOperation(value = "添加测试用例,参数是对象",notes = "插入并测试事务,参数是对象")
    @ApiImplicitParam(name = "job",value = "Job",required = true,dataType = "Job")
    @RequestMapping(value = "/job/add",method = RequestMethod.POST)
    public String jobAdd(@RequestBody Job job){
        logger.info("request params:{}",job.toString());
        return "ok";
    }

    @ApiOperation(value = "加密测试",notes = "")
//    @ApiImplicitParam(name = "content",value = "content")
    @RequestMapping(value = "/cryto/encr",method = RequestMethod.GET)
    public String encrypt(@RequestParam String content,@RequestParam String key){
        logger.info("request params:{}",content.toString());
        String reslutstr="";
        try {
            long start=System.currentTimeMillis();
            byte[] result = AESUtil.encrypt(content,key);
            long end=System.currentTimeMillis();
            System.out.println("加密后 = " +new String(result)+",cost:"+(end-start));
            reslutstr = AESUtil.byte2hex(result);
            System.out.println("加密后 hex= " +reslutstr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reslutstr;
    }
    @ApiOperation(value = "解密测试",notes = "")
//    @ApiImplicitParam(name = "content",value = "content")
    @RequestMapping(value = "/cryto/dec",method = RequestMethod.GET)
    public String decrypt(@RequestParam String content,@RequestParam String key){
        logger.info("request params:{}",content.toString());
        byte[] result = new byte[0];
        try {
            long start=System.currentTimeMillis();
            byte[] content_byte = AESUtil.hex2byte(content);
             result = AESUtil.decrypt(content_byte,key);
            long end=System.currentTimeMillis();
            System.out.println("解密后 = " +new String(result)+",cost:"+(end-start));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }



    @ApiOperation(value = "",notes = "")
//    @ApiImplicitParam(name = "a",value = "a",required = true,dataType = "int")
    @RequestMapping(value = "/test/add",method = RequestMethod.POST)
    public String test(@RequestBody Job a){
        logger.info("request params:{}",a);
        return "ok";
    }




}
