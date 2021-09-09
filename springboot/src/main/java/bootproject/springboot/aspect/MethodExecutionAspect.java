package bootproject.springboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

// Configuration related to aop
@Aspect
@Configuration
public class MethodExecutionAspect {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around(
			value = "bootproject.springboot.aspect.CommonJointPoint.trackTimeAnnotaion()")
	public void afterReturning(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		joinPoint.proceed();
		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("Time taken by {} is {}",joinPoint,timeTaken);
	}
	
	
}
