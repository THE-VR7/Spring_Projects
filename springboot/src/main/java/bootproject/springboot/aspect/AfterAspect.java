package bootproject.springboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

// Configuration related to aop
@Aspect
@Configuration
public class AfterAspect {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterReturning(
			value = "bootproject.springboot.aspect.CommonJointPoint.businessLayerExecution()",
			returning="result")
	public void after(JoinPoint joinPoint, Object result ) {
		logger.info("{} returned with value {}",joinPoint,result);
	}
	
	@AfterThrowing(
			value = "bootproject.springboot.aspect.CommonJointPoint.businessLayerExecution()",
			throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Exception exception) {
		logger.info("{} returned with value {}",joinPoint,exception);
	}
	
	@After(
			value = "bootproject.springboot.aspect.CommonJointPoint.businessLayerExecution()"
			)
	public void after(JoinPoint joinPoint) {
		logger.info("{} returned without value",joinPoint);
	}
	
}
