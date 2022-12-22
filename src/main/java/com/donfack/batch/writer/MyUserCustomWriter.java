package com.donfack.batch.writer;

import com.donfack.manager.model.Manager;
import com.donfack.user.model.User1;
import com.donfack.user.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyUserCustomWriter implements ItemWriter<User1> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void write(List<? extends User1> list) throws Exception {
        for (User1 data : list) {
            System.out.println("MyCustomWriter    : Writing data    : "
                    + data.getId()+" : "
                    +data.getName()+" : "
                    +data.getPassword()+" : "
                    +data.getDate()+" : "
                    +data.getSalary());
            userRepository.save(data);

        }
    }
}
