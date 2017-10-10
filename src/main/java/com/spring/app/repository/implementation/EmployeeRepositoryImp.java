package com.spring.app.repository.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;
import com.spring.app.pojo.Employee;

@Service
public class EmployeeRepositoryImp {

	@Autowired(required = true)
	MongoTemplate mongoTemplate;

	/**
	 * Get all the employee(s) details.
	 */
	public List<Employee> getAllEmployees() {
		return mongoTemplate.findAll(Employee.class);
	}

	/**
	 * Get the employee(s) details based on search criteria.
	 */
	public List<Employee> getEmployeesBySearchCriteria(Employee employee) {

		List<Employee> employeesList = null;
		try {

			Integer empId = employee.employeeId;
			String emailId = employee.emailId;
			String firstName = employee.firstName;
			String lastName = employee.lastName;

			/*
			 * Query query = new Query(); //db.inventory.find( { $or: [ { status: "A" }, {
			 * qty: { $lt: 30 } } ] } );
			 * 
			 * if(empId != null && empId > 0)
			 * query.addCriteria(Criteria.where("employeeId").is(empId));
			 * 
			 * 
			 * if(emailId != null && !emailId.trim().equals(""))
			 * query.addCriteria(Criteria.where("emailId").is(emailId));
			 * 
			 * 
			 * if(firstName != null && !firstName.trim().equals(""))
			 * query.addCriteria(Criteria.where("firstName").is(firstName));
			 * 
			 * if(lastName != null && !lastName.trim().equals(""))
			 * query.addCriteria(Criteria.where("lastName").is(lastName));
			 * 
			 * employeesList = mongoTemplate.find(query, Employee.class);
			 */

			Criteria criteria = new Criteria();

			List<Criteria> criteriaList = new ArrayList<>();

			if (empId != null && empId > 0)
				criteriaList.add(Criteria.where("employeeId").is(empId));

			if (emailId != null && !emailId.trim().equals(""))
				criteriaList.add(Criteria.where("emailId").is(emailId));

			if (firstName != null && !firstName.trim().equals(""))
				criteriaList.add(Criteria.where("firstName").is(firstName));

			if (lastName != null && !lastName.trim().equals(""))
				criteriaList.add(Criteria.where("lastName").is(lastName));

			// criteria = criteria.orOperator(criteriaList.toArray(new
			// Criteria[criteriaList.size()]));
			//
			// employeesList = mongoTemplate.find(Query.query(criteria), Employee.class);

			int listSize = criteriaList.size();

			if (listSize == 4) {
				criteria.orOperator(criteriaList.get(0), criteriaList.get(1), criteriaList.get(2), criteriaList.get(3));
			} else if (listSize == 3) {
				criteria.orOperator(criteriaList.get(0), criteriaList.get(1), criteriaList.get(2));
			} else if (listSize == 2) {
				criteria.orOperator(criteriaList.get(0), criteriaList.get(1));
			} else if (listSize == 1) {
				criteria.orOperator(criteriaList.get(0));
			}

			employeesList = mongoTemplate.find(Query.query(criteria), Employee.class);

			// employeesList = mongoTemplate.find(Query.query(new Criteria()
			// .orOperator(Criteria.where("employeeId").is(empId),
			// Criteria.where("emailId").is(emailId),
			// Criteria.where("firstName").is(firstName),
			// Criteria.where("lastName").is(lastName)) ), Employee.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeesList;
	}

	/**
	 * Saves a {@link Employee}.
	 */
	public String saveEmployee(Employee employee) {

		String saveStatus = "false";
		try {

			mongoTemplate.insert(employee);
			saveStatus = "true";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveStatus;
	}

	/**
	 * Gets a {@link Employee} for a particular id.
	 */
	public Employee getEmployee(String employeeId) {

		Employee employee = null;

		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("employeeId").is(Integer.parseInt(employeeId)));

			employee = mongoTemplate.findOne(query, Employee.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employee;

	}

	/**
	 * The method will hit into the DB and check the details with the input
	 * parameters
	 */
	public Employee getEmployeeByEmail(String emailId, String password) {

		Employee employee = null;

		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("emailId").is(emailId));

			employee = mongoTemplate.findOne(query, Employee.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employee;
	}

	/**
	 * Delete a {@link Employee} for a particular id.
	 */

	public String deleteEmployee(String employeeId) {

		String deleteStaus = "false";

		try {

			Query query = new Query();
			query.addCriteria(Criteria.where("employeeId").is(Integer.parseInt(employeeId)));

			mongoTemplate.remove(query, Employee.class);

			deleteStaus = "true";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return deleteStaus;

	}

}
