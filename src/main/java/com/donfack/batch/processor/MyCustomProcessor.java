package com.donfack.batch.processor;
import com.donfack.manager.model.Manager;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.donfack.employee.model.Employee;
import com.donfack.user.model.User1;

@Component
public class MyCustomProcessor implements ItemProcessor<Employee, Manager>{


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Manager process(Employee emp) throws Exception {
        String pw=emp.getPassword();
        if (pw.isEmpty()){
            System.err.println("password is empty");
        }else {
            System.err.println("password is not empty");
        }

        System.out.println("MyBatchProcessor : Processing data : "+emp);
        Manager manager = new Manager();
        manager.setId(emp.getId());
        //manager.setName(emp.getName().toUpperCase());
        manager.setName(emp.getName());
        manager.setSalary(emp.getSalary());
        manager.setPassword(passwordEncoder.encode(pw));
        manager.setDate(emp.getDate());
        return manager;
    }


}
