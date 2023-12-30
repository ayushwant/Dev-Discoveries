package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// write comments for this code


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeRestControllerTest {

    private MockMvc mockMvc;
    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    static EmployeeRestController employeeRestController;

     static List<Employee> employeeList = new ArrayList<>();
    private final ObjectWriter objectwriter = new ObjectMapper().writer();

    @BeforeAll
    public void setUp(){

        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeRestController).build();
    }

    @Test
    void getEmployees() throws Exception {

        employeeList.add(new Employee("John", "Doe", "john.doe@gmail.com"));
        employeeList.add(new Employee("A", "B", "a.b@gmail.com"));

        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/demo/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].first_name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].first_name").value("A"));
    }

    @Test
    void getEmployeeById() {
    }

    @Test
    void saveEmployee() throws Exception {

        Employee employee = new Employee( "C", "D", "c.d@gmail.com");

        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

//        Solution
//        1. Mockito uses the equals for argument matching, try using ArgumentMatchers.any for the save method.
//        2. Alternatively, implements both equals and hashCode for the Model.
//        3. Or, use the same instance of the model for the save method.

        Mockito.when(employeeRepository.save(Mockito.any(Employee.class)))
                .thenReturn(employee);

        String content = objectwriter.writeValueAsString(employee);

        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.post("/demo/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.first_name").value("C"));

    }
}