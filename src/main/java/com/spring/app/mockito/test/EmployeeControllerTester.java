package com.spring.app.mockito.test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.spring.app.controller.EmployeeController;
import com.spring.app.pojo.Employee;
import com.spring.app.pojo.User;
import com.spring.app.repository.EmployeeRepository;
import com.spring.app.repository.implementation.EmployeeRepositoryImp;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTester {

	@InjectMocks
	EmployeeController controller = new EmployeeController();

	@Mock
	EmployeeRepositoryImp employeeRepositoryImp;

	@Mock
	EmployeeRepository employeeRepository;

	Map<String, Object> model;
	Employee actualEmpObject;
	Employee inputEmpObject = new Employee();
	User user = new User();

	@Test
	public void authenticationTest() {

		String actualMessage = "";

		/**
		 * Case 1 --> with valid details
		 */
		user.setUsername("gthota@afb.com");
		user.setPassword("hello");

		inputEmpObject.setFirstName("GOPI");
		inputEmpObject.setLastName("NATH");

		when(employeeRepositoryImp.getEmployeeByEmail(user.getUsername(), user.getPassword()))
				.thenReturn(inputEmpObject);

		model = controller.home(user);

		actualEmpObject = (Employee) model.get("employee");
		actualMessage = (String) model.get("message");

		Assert.assertEquals("success", actualMessage);
		Assert.assertEquals(inputEmpObject.getFirstName(), actualEmpObject.getFirstName());

		/**
		 * Case 2 --> username is empty
		 */
		user.setUsername("");
		user.setPassword("");

		when(employeeRepositoryImp.getEmployeeByEmail(user.getUsername(), user.getPassword()))
				.thenReturn(inputEmpObject);

		model = controller.home(user);
		actualMessage = (String) model.get("message");

		Assert.assertEquals("Username is required", actualMessage);

		/**
		 * Case 3 ---> password field is empty
		 */
		user.setUsername("abc@xyz.com");
		user.setPassword("");

		when(employeeRepositoryImp.getEmployeeByEmail(user.getUsername(), user.getPassword()))
				.thenReturn(inputEmpObject);

		model = controller.home(user);
		actualMessage = (String) model.get("message");

		Assert.assertEquals("Password is required", actualMessage);

		/**
		 * Case 4 --> empty employee object with out data return
		 */
		user.setUsername("aaa@aa.com");
		user.setPassword("gopi");

		when(employeeRepositoryImp.getEmployeeByEmail(user.getUsername(), user.getPassword())).thenReturn(null);

		model = controller.home(user);
		actualMessage = (String) model.get("message");

		Assert.assertEquals("Invalid User", actualMessage);

		/**
		 * Case 6 --> Exception Case
		 */

		doThrow(new RuntimeException()).when(employeeRepositoryImp).getEmployeeByEmail(user.getUsername(),
				user.getPassword());

		model = controller.home(user);
		actualMessage = (String) model.get("message");

		Assert.assertEquals("Exception Occured", actualMessage);
	}

	@Test
	public void saveEmployeeDetailsTest() {

		/**
		 * Case 1 --> save employee object with complete data
		 */
		inputEmpObject.setEmployeeId(222);
		inputEmpObject.setFirstName("GOPI");
		inputEmpObject.setLastName("NATH");
		inputEmpObject.setPhoneNumber("1111");
		inputEmpObject.setEmailId("sssss");

		List<Employee> empList = new ArrayList<Employee>();

		when(employeeRepository.save(inputEmpObject)).thenReturn(inputEmpObject);

		model = controller.saveEmployeeDetails(inputEmpObject);

		String status = (String) model.get("message");
		empList.add(inputEmpObject);

		Assert.assertEquals(1, empList.size());

		Assert.assertEquals("Saved successfully", status);

		/**
		 * Case 2 --> employee object with empty data
		 */

		when(employeeRepository.save(inputEmpObject)).thenReturn(actualEmpObject);

		model = controller.saveEmployeeDetails(actualEmpObject);

		status = (String) model.get("message");

		Assert.assertEquals("Empty Details", status);

		/**
		 * Case 3 --> employee object with mismatched data
		 */

		when(employeeRepository.save(inputEmpObject)).thenReturn(actualEmpObject);

		model = controller.saveEmployeeDetails(inputEmpObject);

		status = (String) model.get("message");

		Assert.assertEquals("Failed to save", status);

		/**
		 * Case 4 --> Exception Case
		 */

		doThrow(new RuntimeException()).when(employeeRepository).save(inputEmpObject);

		model = controller.saveEmployeeDetails(inputEmpObject);
		status = (String) model.get("message");

		Assert.assertEquals("Exception Occured", status);

	}

	@Test
	public void getEmployeeDetailsTest() {

		/**
		 * Case 1 --> employee object with complete data
		 */
		inputEmpObject.setEmployeeId(222);
		inputEmpObject.setFirstName("GOPI");
		inputEmpObject.setLastName("NATH");
		inputEmpObject.setPhoneNumber("1111");
		inputEmpObject.setEmailId("sssss");

		List<Employee> empList = new ArrayList<Employee>();
		empList.add(inputEmpObject);

		when(employeeRepositoryImp.getAllEmployees()).thenReturn(empList);

		model = controller.getEmployeeDetails();

		String status = (String) model.get("message");

		Assert.assertEquals("Records found", status);

		/**
		 * Case 2 --> employee object with empty data
		 */

		empList = new ArrayList<Employee>();

		when(employeeRepositoryImp.getAllEmployees()).thenReturn(empList);

		model = controller.getEmployeeDetails();

		status = (String) model.get("message");

		Assert.assertEquals("No records found", status);

		/**
		 * Case 3 --> Exception Case
		 */

		doThrow(new RuntimeException()).when(employeeRepositoryImp).getAllEmployees();

		model = controller.getEmployeeDetails();
		status = (String) model.get("message");

		Assert.assertEquals("Exception Occured", status);
	}

	@Test
	public void viewEmployeeDetailsTest() {

		/**
		 * Case 1 --> employee object with complete data
		 */
		inputEmpObject.setEmployeeId(222);
		inputEmpObject.setFirstName("GOPI");
		inputEmpObject.setLastName("NATH");
		inputEmpObject.setPhoneNumber("1111");
		inputEmpObject.setEmailId("sssss");

		when(employeeRepositoryImp.getEmployee("222")).thenReturn(inputEmpObject);

		model = controller.viewEmployeeDetails("222");

		String status = (String) model.get("message");
		Assert.assertEquals("Matched", status);

		/**
		 * Case 2 --> employee object with mismatched data
		 */

		when(employeeRepositoryImp.getEmployee("222")).thenReturn(inputEmpObject);

		model = controller.viewEmployeeDetails("111");

		status = (String) model.get("message");
		Assert.assertEquals("Not matched", status);

		/**
		 * Case 3 --> employee object with empty data
		 */

		when(employeeRepositoryImp.getEmployee("222")).thenReturn(inputEmpObject);

		model = controller.viewEmployeeDetails("");

		status = (String) model.get("message");
		Assert.assertEquals("Empty Employee Id", status);

		/**
		 * Case 4 --> employee object with empty spaces data
		 */

		when(employeeRepositoryImp.getEmployee("222")).thenReturn(inputEmpObject);

		model = controller.viewEmployeeDetails("    ");

		status = (String) model.get("message");
		Assert.assertEquals("Empty Employee Id", status);

		/**
		 * Case 5 --> Exception Case
		 */

		doThrow(new RuntimeException()).when(employeeRepositoryImp).getEmployee("222");

		model = controller.viewEmployeeDetails("222");
		status = (String) model.get("message");

		Assert.assertEquals("Exception Occured", status);

	}

	@Test
	public void deleteEmployeeDetailsTest() {

		/**
		 * Case 1 --> with valid data
		 */

		when(employeeRepositoryImp.deleteEmployee("222")).thenReturn("true");

		model = controller.deleteEmployeeDetails("222");

		String status = (String) model.get("message");
		Assert.assertEquals("Deleted Successfully", status);

		/**
		 * Case 2 --> with mismatched data
		 */

		when(employeeRepositoryImp.deleteEmployee("333")).thenReturn("false");

		model = controller.deleteEmployeeDetails("333");

		status = (String) model.get("message");
		Assert.assertEquals("Failed to Delete", status);

		/**
		 * Case 3 --> employee object with empty spaces data
		 */

		when(employeeRepositoryImp.deleteEmployee("222")).thenReturn("true");

		model = controller.deleteEmployeeDetails("    ");

		status = (String) model.get("message");
		Assert.assertEquals("Empty Employee Id", status);

		/**
		 * Case 4 --> employee object with empty Id
		 */

		when(employeeRepositoryImp.deleteEmployee("222")).thenReturn("true");

		model = controller.deleteEmployeeDetails("");

		status = (String) model.get("message");
		Assert.assertEquals("Empty Employee Id", status);

		/**
		 * Case 5 --> Exception Case
		 */

		doThrow(new RuntimeException()).when(employeeRepositoryImp).deleteEmployee("222");

		model = controller.deleteEmployeeDetails("222");
		status = (String) model.get("message");

		// Assert.assertEquals("Exception Occured", status);

	}
}
