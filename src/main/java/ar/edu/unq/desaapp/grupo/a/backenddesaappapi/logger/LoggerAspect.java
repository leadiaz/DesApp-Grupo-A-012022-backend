package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.logger;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.BackendDesaappApiApplication;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {
    @Around("execution(* ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices..*(..))")
    public Object logApiData(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String[] parameterNames = methodSignature.getParameterNames();
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        BackendDesaappApiApplication.logger.info("Execution time of " + className + "." + methodName + " params " + Arrays.toString(parameterNames) + " :: " + stopWatch.getTotalTimeMillis() + " ms");
        return result;
    }
}