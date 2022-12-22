package com.donfack.batch.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.donfack.employee.model.Employee;
import com.donfack.manager.model.Manager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MyCustomProcessor.class})
@ExtendWith(SpringExtension.class)
class MyCustomProcessorTest {
    @Autowired
    private MyCustomProcessor myCustomProcessor;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void test_Migrating_EmployeeData_To_ManagerDatabase1() throws Exception {
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        Employee employee = new Employee();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        employee.setDate(fromResult);
        employee.setId(123L);
        employee.setName("Name");
        employee.setPassword("iloveyou");
        employee.setSalary(1);
        Manager actualProcessResult = myCustomProcessor.process(employee);
        assertSame(fromResult, actualProcessResult.getDate());
        assertEquals(1, actualProcessResult.getSalary());
        assertEquals("secret", actualProcessResult.getPassword());
        assertEquals("Name", actualProcessResult.getName());
        assertEquals(123L, actualProcessResult.getId().longValue());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    @Test
    void test_Migrating_EmployeeData_To_ManagerDatabase2() throws Exception {
        //When Exist
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        //GIVEN
        Employee employee = mock(Employee.class);
        //WHEN
        when(employee.getSalary()).thenReturn(1);
        when(employee.getId()).thenReturn(123L);
        when(employee.getName()).thenReturn("Name");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        when(employee.getDate()).thenReturn(fromResult);
        when(employee.getPassword()).thenReturn("");
        //Then
        doNothing().when(employee).setDate((Date) any());
        doNothing().when(employee).setId((Long) any());
        doNothing().when(employee).setName((String) any());
        doNothing().when(employee).setPassword((String) any());
        doNothing().when(employee).setSalary(anyInt());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        employee.setDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        employee.setId(123L);
        employee.setName("Name");
        employee.setPassword("iloveyou");
        employee.setSalary(1);
        Manager actualProcessResult = myCustomProcessor.process(employee);
        assertSame(fromResult, actualProcessResult.getDate());
        assertEquals(1, actualProcessResult.getSalary());
        assertEquals("secret", actualProcessResult.getPassword());
        assertEquals("Name", actualProcessResult.getName());
        assertEquals(123L, actualProcessResult.getId().longValue());
        verify(passwordEncoder).encode((CharSequence) any());
        verify(employee).getSalary();
        verify(employee).getId();
        verify(employee).getName();
        verify(employee).getPassword();
        verify(employee).getDate();
        verify(employee).setDate((Date) any());
        verify(employee).setId((Long) any());
        verify(employee).setName((String) any());
        verify(employee).setPassword((String) any());
        verify(employee).setSalary(anyInt());
    }
}

