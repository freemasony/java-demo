package com.dom4j;

import com.utils.JsonSerializeUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoujian on 2018/2/5.
 */
public class DomXml {

    public static void main(String[] args) throws DocumentException {
        List<Map<String,Object>> list=new ArrayList<>();
        SAXReader sax=new SAXReader();//创建一个SAXReader对象
        File xmlFile=new File("c:\\strings.xml");//根据指定的路径创建file对象
        Document document=sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
        Element root=document.getRootElement();//获取根节点

        Element roots = document.getRootElement();
        List<Element> childElements = roots.elements();
        for (Element child : childElements) {
            Map<String,Object> map=new HashMap<>();
            if(child.attribute("name")!=null){
                String name=child.attribute("name").getText().trim();
                map.put(name,child.getStringValue());
                list.add(map);
            }
        }

        System.out.println("***"+ JsonSerializeUtil.objectToJson(list));

    }
}
