package belajar.java.application;

import belajar.java.data.Company;

public class CompanyApp {
    public static void main(String[] args) {

        Company company = new Company();
        company.setName("Fake Company");

        Company.Employee employee = company.new Employee();
        employee.setName("Fake Employee");

        System.out.println(employee.getName());
        System.out.println(employee.getCompany());

        Company company2 =  new Company();
        company2.setName("Fake Company2");

        Company.Employee employee2 = company2.new Employee();
        employee2.setName("Fake Employee2");

        System.out.println(employee2.getName());
        System.out.println(employee2.getCompany());
    }
}
