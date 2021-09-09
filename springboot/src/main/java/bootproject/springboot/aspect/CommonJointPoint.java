package bootproject.springboot.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJointPoint {

	@Pointcut("execution(* bootproject.springboot..*.*(..))")
	public void dataLayerExecution() {}

	@Pointcut("execution(* bootproject.springboot.business.*.*(..))")
	public void businessLayerExecution() {}
	
	@Pointcut("@annotation(bootproject.springboot.aspect.TrackTime)")
	public void trackTimeAnnotaion() {}
	
}
