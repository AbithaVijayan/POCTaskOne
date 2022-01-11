package com.neosoft.controller;

import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.model.Employee;
import com.neosoft.exception.ResourceNotFoundException;
import com.neosoft.repository.EmployeeRepository;

@SuppressWarnings("unused")
@RestController
public class EmployeeController {

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
		// http://localhost:8080/employees
		@GetMapping("/employees")
		public Object getAllEmployees() {
			 return employeeRepository.findAll().stream().filter(el->el.getDeleted()==0);
		}
		
		
		// http://localhost:8080/employees/sortByDOBAndJDate"
		@GetMapping("/employees/sortByDOBAndJDate")
		public List<Employee> getAllEmployeesSortByDobAndJDate() {
			return employeeRepository.findAllByOrderByDobAsc();
		}
		
		// http://localhost:8080/employees/save
		@PostMapping("/employees/save")
		public void registerEmployee(@RequestBody Employee employee) {
			employeeRepository.save(employee);
		}
		
		// http://localhost:8080/employees/and/Abitha/Vijayan/560077
		@GetMapping("/employees/and/{firstname}/{surname}/{pincode}")
		public List<Employee> getEmployeesByfirstnameOrlastnameOrpincode(@PathVariable String firstname, @PathVariable String surname,@PathVariable String pincode) {
			return employeeRepository.findByFirstnameOrSurnameOrPincode(firstname, surname,pincode);
		}
		
		//http://localhost:8080/employees/firstname/Abitha
		@GetMapping("/employees/firstname/{firstname}")
		public List<Employee> getEmployeesByFirstName(@PathVariable String firstname){
			return employeeRepository.findByFirstname(firstname);
		}
		
		//http://localhost:8080/employees/surname/Vijayan
		@GetMapping("/employees/surname/{surname}")
		public List<Employee> getEmployeesBySurname(@PathVariable String surname){
			return employeeRepository.findBySurname(surname);
		}
		
		//http://localhost:8080/employees/pincode/560077
		@GetMapping("/employees/pincode/{pincode}")
		public List<Employee> getEmployeesByPincode(@PathVariable String pincode){
			return employeeRepository.findByPincode(pincode);
		}
		
       //http://localhost:8080/emloyees/1
	   @PutMapping("/emloyees/{id}")
	   public Employee updatePost(@PathVariable Long id, @Valid @RequestBody Employee employeeReq) {
		    return employeeRepository.findById(id).map(employee -> {
	        employee.setFirstname(employeeReq.getFirstname());
			employee.setSurname(employeeReq.getSurname());
			employee.setDob(employeeReq.getDob());
			employee.setDoj(employeeReq.getDoj());
			employee.setAddress(employeeReq.getAddress());
			employee.setPincode(employeeReq.getPincode());
			employee.setDeleted(employeeReq.getDeleted());
			return employeeRepository.save(employee);
		    }).orElseThrow(() -> new ResourceNotFoundException("Employee", "EmployeeId", id));
	}
		
		
	    //http://localhost:8080/emloyees/soft/{id}
	    @DeleteMapping("/emloyees/soft/{id}")
	    public void softdeleteEmployee(@PathVariable long id) {
			employeeRepository.softDelete(id);
	    }
		
		// http://localhost:8080/emloyees/hard/{id}
		@DeleteMapping("/emloyees/hard/{id}")
		public void harddeleteEmployee(@PathVariable long id) {
			employeeRepository.deleteById(id);
		}
			
	
	
	
}
