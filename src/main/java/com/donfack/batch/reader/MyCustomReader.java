package com.donfack.batch.reader;

import com.donfack.employee.model.Employee;
import com.donfack.user.model.User1;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class MyCustomReader extends JdbcCursorItemReader<Employee> implements ItemReader<Employee>{



    public MyCustomReader(@Autowired DataSource primaryDataSource) {
        setDataSource(primaryDataSource);
        setSql("SELECT id, name, salary, password, date FROM employee");
        setFetchSize(100);
        setRowMapper(new EmployeeRowMapper());
    }

    public class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee  = new Employee();
            employee.setId(rs.getLong("id"));
            employee.setName(rs.getString("name"));
            employee.setPassword(rs.getString("password"));
            employee.setDate(rs.getDate("date"));
            employee.setSalary(rs.getInt("salary"));
            return employee;
        }
    }
}
