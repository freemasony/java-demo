package com.controller;

import com.gqhmt.mdm.device.service.DeviceService;
import com.gqhmt.mdm.entity.DeviceInfo;
import com.gqhmt.mdm.jpush.service.JPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhoujian on 2017/12/5.
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/api")
public class AdminController {

    @Autowired
    private JPushService jPushService;
    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "admin",method = RequestMethod.GET)
    @ResponseBody
    public Object queryCmsAdList(HttpServletRequest request, String mobile,String content){

        DeviceInfo deviceInfo=new DeviceInfo();
        deviceInfo.setUserId(891960678);
        deviceInfo.setDevicePlatform("android");
        deviceInfo.setXgDeviceId("c9b1423414f0d4b3d8b3aed4a1520257a72c2b24");
        deviceInfo=deviceService.queryDeviceInfo(deviceInfo);


        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0000");
        resultMap.put("msg", "sucess");
        resultMap.put("data",deviceInfo);
        return resultMap;
    }

}
