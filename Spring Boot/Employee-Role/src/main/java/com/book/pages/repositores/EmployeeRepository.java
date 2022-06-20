package com.book.pages.repositores;

import com.book.pages.model.*;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	public Employee save(Employee employee);
}