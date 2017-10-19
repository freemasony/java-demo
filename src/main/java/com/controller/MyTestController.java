package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Service.AccountService;
import com.Service.EmailService;
import com.Service.ProductService;
import com.Service.RegisterService;
import com.entity.Admin;
import com.entity.Product;
import com.entity.ShiroUser;
import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/mytest")
public class MyTestController {

	@Autowired
	private ProductService productService;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "test")
	public String test(Model model, ServletRequest request,Admin admins) {
		
//		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();

		Product product = productService.findOneByProductId(1000000001L);
		if (product != null) {
			System.out.println(product.getProductName());
		}
		model.addAttribute("product", product);
//		model.addAttribute("user", user);
		Admin admin=accountService.getAdminUserByLoginName("zhoujian");
//		registerService.publishRegisterEvent(admin);
		emailService.pulishEmailEvent(admin);
		 
		System.out.println("end...");
		
//		product=null;
		 
//		 Long id = product.getId();
		 
		
		
		return "/mytest/mytestlist";
	}

	@RequestMapping(value = "download", method = { RequestMethod.GET })
	public void download(HttpServletResponse response)
			throws IOException {
		String path = request.getSession().getServletContext()
				.getRealPath("static/images/111.jpg");
		String filename = "111.jpg";
		filename = URLEncoder.encode(filename, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ filename);
		java.io.OutputStream out = null;
		FileInputStream in = null;
		try {
			out = response.getOutputStream();
			in = new FileInputStream(path);

			byte[] b = new byte[1024];
			int i = 0;

			while ((i = in.read(b)) > 0) {
				out.write(b, 0, i);
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("Error!");
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
				in = null;
			}

			if (in != null) {
				in.close();
				in = null;
			}
		}

	}
	
	public static void main(String[] args) {
		
		try {
			FileInputStream file=new FileInputStream("c:\\1.xlsx");
			readUploadTemplete(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	 public  static List<Long> readUploadTemplete(FileInputStream file) throws IOException {
	        List<Long> list=Lists.newLinkedList();
	        XSSFWorkbook xwb = new XSSFWorkbook(file);
	        // ��ȡ��һ�±������
	        XSSFSheet sheet = xwb.getSheetAt(0);
	        // ���� row��cell
	        XSSFRow row;
	        double cell;
	        // ѭ���������е�����
	        for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
	            row = sheet.getRow(i);
	            for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
	                System.out.println("******cell***i="+i+"***j="+j);
	                // ͨ�� row.getCell(j).toString() ��ȡ��Ԫ�����ݣ�
	                cell = row.getCell(j).getNumericCellValue();
	                long userId=new Double(cell).longValue();
	                list.add(userId);
	                System.out.print(userId + "\t");
	            }
	            System.out.println("");
	        }
	        return list;
	    }

}
