package com.weasley.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.weasley.data.Customer;
import com.weasley.data.CustomerDAO;

@ManagedBean(name = "customerManagedBean")
@SessionScoped
public class CustomerManagedBean {
	private Customer customer = new Customer();
	private List<Customer> customers = new ArrayList<>();
	private CustomerDAO dao = new CustomerDAO();
	
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String findByLastName() {
		setCustomers(dao.findByLastName(getCustomer().getLastName()));
		return "";
	}
	
	public String findById() {
		setCustomer(dao.findById(getCustomer().getCustomerId()));
		return "";
	}
	
	public String registerCustomer() {
		dao.insert(customer);
		return "";
	}
}
