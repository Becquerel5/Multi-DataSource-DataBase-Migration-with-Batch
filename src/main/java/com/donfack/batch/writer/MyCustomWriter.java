package com.donfack.batch.writer;

import com.donfack.employee.model.Employee;
import com.donfack.employee.repository.EmployeeRepository;
import com.donfack.manager.model.Manager;
import com.donfack.manager.repository.ManagerRepository;
import com.donfack.user.model.User1;
import com.donfack.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.batch.item.ItemWriter;
import java.util.List;



@Component
public class MyCustomWriter implements ItemWriter<Manager>  {

    @Autowired
    ManagerRepository managerRepository ;


    @Override
    public void write(List<? extends Manager> list) throws Exception {
        for (Manager data : list) {
            System.out.println("MyCustomWriter    : Writing data    : "
                    + data.getId()+" : "
                    +data.getName()+" : "
                    +data.getPassword()+" : "
                    +data.getDate()+" : "
                    +data.getSalary());
            managerRepository.save(data);

        }

    }



}
