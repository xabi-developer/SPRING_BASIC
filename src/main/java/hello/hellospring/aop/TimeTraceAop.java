package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component // -> 컴포넌트 방식도 가능하지만, 일반적으로 AOP는 사용 중임을 쉽게 인지할 수 있게 Config 클래스에 @Bean으로 스프링 빈으로 직접 등록한다.
@Aspect
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 실행할 경로
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }

}