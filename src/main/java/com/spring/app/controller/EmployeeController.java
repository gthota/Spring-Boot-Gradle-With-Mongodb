package com.spring.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.app.pojo.Employee;
import com.spring.app.pojo.User;
import com.spring.app.repository.EmployeeRepository;
import com.spring.app.repository.implementation.EmployeeRepositoryImp;

@RestController
public class EmployeeController {

	@Autowired(required = true)
	private EmployeeRepositoryImp employeeRepositoryImp;
	
	@Autowired(required = true)
	private EmployeeRepository employeeRepository;

	@RequestMapping("/")
	public ModelAndView parent(Map<String, Object> model) {
		model.put("message", "Welcome Reader !!");
		return new ModelAndView("parentPage");
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> employeeInput() {

		System.out.println("Input Page");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("message", "Input Page");

		return model;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> employeeHome() {

		System.out.println("Home Page");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("message", "Home Page");

		return model;
	}

	/**
	 * The below method is used to check the whether the user is valid or not. The
	 * action comes from the Login Page
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> home(@RequestBody User user) {

		Map<String, Object> model = new HashMap<String, Object>();
	  
		try {
			String username = user.getUsername();
			String password = user.getPassword();
			

			if (username == null || username.equals("")) {
				model.put("message", "Username is required");
				return model;
			}

			if (password == null || password.equals("")) {
				model.put("message", "Password is required");
				return model;
			}
			/**
			 * The below method which is used to check the user input details If user is
			 * valid then it will return the employee object Other wise it will return empty
			 * employe object
			 */
			Employee employee = employeeRepositoryImp.getEmployeeByEmail(username, password);

			if (employee != null) {
				model.put("message", "success");
				model.put("employee", employee);
			} else {
				model.put("message", "Invalid User");
			}

		} catch (Exception e) {
			model.put("message", "Exception Occured");
			e.printStackTrace();
		}

		return model;
	}

	/**
	 * Which is for to save the employee details coming from the client side
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveEmployeeDetails(@RequestBody Employee employee) {

		Map<String, Object> model = new HashMap<String, Object>();

		try {
			if (employee == null) {
				model.put("message", "Empty Details");
				return model;
			}

			/**
			 * The below method will save the employee object and return the Saved employee
			 * object
			 */
			 employee = employeeRepository.save(employee);

			if (employee != null) {
				model.put("message", "Saved successfully");
			} else {
				model.put("message", "Failed to save");
			}

			/**
			 * The below method will get total employee from the DB and give as a response
			 * to show in the client side
			 */
			List<Employee> empList = employeeRepositoryImp.getAllEmployees();

			if (empList != null && !empList.isEmpty() && empList.size() > 0) {
				model.put("employeeList", empList);
			}

		} catch (Exception e) {
			model.put("message", "Exception Occured");
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getEmployeeDetails() {

		System.out.println("Search Page");
		Map<String, Object> model = new HashMap<String, Object>();
		try {

			/**
			 * Below method will fetch the total records from the DB
			 */
			List<Employee> empList = employeeRepositoryImp.getAllEmployees();

			if (empList != null && !empList.isEmpty() && empList.size() > 0) {
				model.put("message", "Records found");
				model.put("employeeList", empList);
			} else {
				model.put("message", "No records found");
			}

		} catch (Exception e) {
			model.put("message", "Exception Occured");
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/searchDetails", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getEmployeeDetailsBySearchCriteria(@RequestBody Employee employee) {

		System.out.println("Search Details Page");
		Map<String, Object> model = new HashMap<String, Object>();
		try {

			/**
			 * Below method will fetch the total records from the DB
			 */
			List<Employee> empList = employeeRepositoryImp.getEmployeesBySearchCriteria(employee);

			if (empList != null && !empList.isEmpty() && empList.size() > 0) {
				model.put("message", "Records found");
				model.put("employeeList", empList);
			} else {
				model.put("message", "No records found");
			}

		} catch (Exception e) {
			model.put("message", "Exception Occured");
			e.printStackTrace();
		}

		return model;
	}

	/**
	 * The below method is the rest service or micro service example
	 * @return
	 */
	
	@RequestMapping(value = "/search1", method = RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getTotalEmployeeDetails() {

		System.out.println("get Details Page");
		Map<String, Object> model = new HashMap<String, Object>();
		try {

			/**
			 * Below method will fetch the total records from the DB
			 */
			List<Employee> empList = employeeRepositoryImp.getAllEmployees();

			if (empList != null && !empList.isEmpty() && empList.size() > 0) {
				model.put("message", "Records found");
				model.put("employeeList", empList);
			} else {
				model.put("message", "No records found");
			}

		} catch (Exception e) {
			model.put("message", "Exception Occured");
			e.printStackTrace();
		}

		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> viewEmployeeDetails(@RequestParam("employeeId") String employeeId) {

		Map<String, Object> model = new HashMap<String, Object>();

		System.out.println("Hai");
		try {

			if (employeeId.trim().equals("")) {
				model.put("message", "Empty Employee Id");
				return model;
			}

			Employee employee = employeeRepositoryImp.getEmployee(employeeId);

			if (employee != null) {
				model.put("message", "Matched");
				model.put("employee", employee);
			} else {
				model.put("message", "Not matched");
			}

		} catch (Exception e) {
			model.put("message", "Exception Occured");
			e.printStackTrace();
		}
		return model;
		
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Map<String, Object> deleteEmployeeDetails(@RequestParam("employeeId") String employeeId) {

		Map<String, Object> model = new HashMap<String, Object>();

		try {

			if (employeeId.trim().equals("")) {
				model.put("message", "Empty Employee Id");
				return model;
			}

			String deleteStatus = employeeRepositoryImp.deleteEmployee(employeeId);

			if (deleteStatus.equals("true")) {
				model.put("message", "Deleted Successfully");
			} else {
				model.put("message", "Failed to Delete");
			}
			model.put("employeeList", employeeRepositoryImp.getAllEmployees());

		} catch (Exception e) {
			model.put("message", "Exception Occured");
			e.printStackTrace();
		}

		return model;
	}
	
}