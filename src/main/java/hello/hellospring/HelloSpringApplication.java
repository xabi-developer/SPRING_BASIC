package hello.hellospring; // 스프링 빈으로 컴포넌트 스캔을 하려면 해당 클래스의 패키지 내 있는 클래스만 가능하다!!

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // @ComponentScan
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
