package br.com.caelum.alura.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerExecucao {

	private final Logger logger;

	public LoggerExecucao() {
		logger = LoggerFactory.getLogger(getClass());
	}

	@Pointcut("execution(* br.com.caelum.alura..service..*(..))")
	public void services() {
	}
	
	@Pointcut("execution(* br.com.caelum.alura.rest..controller..*(..))")
	public void restApi() {
	}

	@Around("services() || restApi()")
	public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
		String classe = joinPoint.getSignature().getDeclaringTypeName();
		String metodo = joinPoint.getSignature().getName();
		Object[] parametros = joinPoint.getArgs();
		try {
			return joinPoint.proceed();
		} finally {
			if (parametros != null && parametros.length > 0)
				logger.info(classe + " - " + metodo + " args: " + parametros[0]);
			else
				logger.info(classe + " - " + metodo);
		}

	}
}
