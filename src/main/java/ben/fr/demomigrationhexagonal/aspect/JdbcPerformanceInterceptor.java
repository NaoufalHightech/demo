package ben.fr.demomigrationhexagonal.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JdbcPerformanceInterceptor {

    @Around("execution(* javax.sql.DataSource.getConnection(..))")
    public Object monitorJdbcOperations(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - startTime;

        // Log or collect the execution time
        System.out.println("Execution time: " + executionTime + "ms");
        return result;
    }
}

