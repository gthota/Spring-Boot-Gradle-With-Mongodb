package com.spring.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.app.pojo.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

	public List<Employee> findAll();

}
