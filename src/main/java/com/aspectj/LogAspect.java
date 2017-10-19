package com.aspectj;

import java.util.Collection;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * http://jinnianshilongnian.iteye.com/blog/1415606 AspectJ切入点语法详解
 * 
 * @author zhoujian 2016-2-17 11:08:12
 * 
 */
@Component
@Aspect
public class LogAspect {

	private static final Logger logger = LoggerFactory
			.getLogger(LogAspect.class);

	/**
	 * Pointcut 定义Pointcut，Pointcut的名称为aspect()，此方法没有返回值和参数 该方法就是一个标识，不进行调用
	 */
	@Pointcut("execution(* com.Service.*.*(..))")
	public void aspect() {

	}

	/*
	   public interface JoinPoint { 
	   String toString(); //连接点所在位置的相关信息 
	   String toShortString(); //连接点所在位置的简短相关信息 
	   String toLongString(); //连接点所在位置的全部相关信息
	   Object getThis(); //返回AOP代理对象 
	   Object getTarget(); //返回目标对象
	   Object[] getArgs(); //返回被通知方法参数列表 
	   Signature getSignature(); //返回当前连接点签名
	   SourceLocation getSourceLocation();//返回连接点方法所在类文件中的位置 
	   String getKind();//连接点类型 
	   StaticPart getStaticPart(); //返回连接点静态部分 
	   }
	 */

	/**
	 * Before 在核心业务执行前执行，不能阻止核心业务的调用。
	 * 
	 * @param joinPoint
	 */
	@Before("aspect()")
	public void beforeAspect(JoinPoint jp) {
		System.out.println("before start...");
//		System.out.println(jp.toString());
//		System.out.println(jp.toShortString());
//		System.out.println(jp.toLongString());

		String classname = jp.getTarget().getClass().toString(); 
		Object[] parames = jp.getArgs();
		try {
			String parame = parseParames(parames); 
			System.out.println("parame ："+parame);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		classname = classname.substring(classname.indexOf("com"));
		String methodName = jp.getSignature().getName();// 获取目标方法名
//		String methodName = signature.substring(signature.lastIndexOf(".") + 1,signature.indexOf("("));
		System.out.println(" classname类名= " + classname+"  methodName= "+methodName); 
		System.out.println("before end...");
	}

	/**
	 * After 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
	 * 
	 * @param joinPoint
	 */
	@After("aspect()")
	public void afterAspect() {
		System.out.println("after...");
	}

	/**
	 * AfterReturning 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
	 * 
	 * @param joinPoint
	 */
	@AfterReturning("aspect()")
	public void AfterReturningAspect() {
		System.out.println("afterReturning...");
	}

	@Around("aspect()")
	public Object aroundAspect(ProceedingJoinPoint pjp) throws Throwable {
		 long start = System.currentTimeMillis();
		Object retval = pjp.proceed();
		  // 记录处理时间
		long end = System.currentTimeMillis()-start;
		System.out.println("处理时间 :"+end);
		return retval;

	}
	
	
	
	@AfterThrowing(value="execution(* com.controller.*.*(..))",throwing="ex")
	public void afterThrowingAspect(JoinPoint jp,Exception ex){
		System.out.println("错误信息 ："+ex.getMessage());
		logger.error("错误信息 : ", ex);
	}
	
	
	
	

	/**
	 * 解析方法参数
	 * 
	 * @param parames
	 *            方法参数
	 * @return 解析后的方法参数
	 */
	private String parseParames(Object[] parames)
			throws JsonProcessingException {
		StringBuffer sb = new StringBuffer();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		for (int i = 0; i < parames.length; i++) {
			if (parames[i] instanceof Object[]
					|| parames[i] instanceof Collection) {

				if (i == parames.length - 1) {
					sb.append(mapper.writeValueAsString(parames[i]));
				} else {
					sb.append(mapper.writeValueAsString(parames[i]) + ",");
				}
			} else {
				if (i == parames.length - 1) {
					sb.append(mapper.writeValueAsString(parames[i]));
				} else {
					sb.append(mapper.writeValueAsString(parames[i]) + ",");
				}
			}
		}
		String params = sb.toString();
		params = params.replaceAll("(\"\\w+\":\"\",)", "");
		params = params.replaceAll("(,\"\\w+\":\"\")", "");
		return params;
	}

}
