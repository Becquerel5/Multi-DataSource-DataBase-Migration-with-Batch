package com.donfack.batch.processor;

import com.donfack.employee.model.Employee;
import com.donfack.manager.model.Manager;
import com.donfack.user.model.User1;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyCustomUserProcessor  implements ItemProcessor<Employee, User1> {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User1 process(Employee employee) throws Exception {
        String pw=employee.getPassword();
        if (pw.isEmpty()){
            System.err.println("password is empty");
        }else {
            System.err.println("password is not empty");
        }

        System.out.println("UserBatchProcessor : Processing UserData : "+employee);
        User1 user1 = new User1();
        user1.setId(employee.getId());
        user1.setName(employee.getName());
        user1.setSalary(employee.getSalary());
        user1.setPassword(passwordEncoder.encode(pw));
        user1.setDate(employee.getDate());
        return user1;
    }
}
