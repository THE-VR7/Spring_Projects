package bootproject.springboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

// Configuration related to aop
@Aspect
@Configuration
public class BeforeAspect {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("bootproject.springboot.aspect.CommonJointPoint.dataLayerExecution()")
	public void before(JoinPoint joinPoint) {
		logger.info("Intercepted message calls {}",joinPoint);
	}
	
}
