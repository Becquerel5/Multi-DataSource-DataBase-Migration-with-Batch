package com.donfack;

import com.donfack.employee.model.Employee;
import com.donfack.employee.repository.EmployeeRepository;
import com.donfack.user.model.User1;
import com.donfack.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Date;

@SpringBootApplication
public class DonfackApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DonfackApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		/*Employee employee =new Employee();
		employee.setName("MA");
		employee.setSalary(543453453);
		employee.setPassword("FBFBDFBDF");
		employee.setDate(Date.from(Instant.now()));
		employeeRepository.save(employee);

		Employee employee1 =new Employee();
		employee1.setName("MA");
		employee1.setSalary(543453453);
		employee1.setPassword("FBFBDFBDF");
		employee1.setDate(Date.from(Instant.now()));
		employeeRepository.save(employee1);*/

		/*User1 user = new User1();
		user.setNumber("5154");
		user.setPassword("LDDDD");
		user.setUsername("DTFB");
		user.setDate(Date.from(Instant.now()));
		userRepository.save(user);

		User1 user1 = new User1();
		user1.setNumber("58653120");
		user1.setPassword("dcsdvsdv");
		user1.setUsername("sdvsdvsddv");
		user1.setDate(Date.from(Instant.now()));
		userRepository.save(user1);*/
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}


}
