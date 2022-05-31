package service;

import model.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StoreService {
    private List<Product> listOfProducts = new ArrayList<>();
    private List<Branch> listOfBranches = new ArrayList<>();
    private List<Employee> listOfEmployees = new ArrayList<>();
    private List<Role> listOfRoles = new ArrayList<>();

    public void addTestData() throws ParseException {
        DateFormat warrantyDate = new SimpleDateFormat("dd/MM/yyyy");
        warrantyDate.parse("01/01/2030");
        listOfBranches.add(new Branch("Hyderabad", "jack", Arrays.asList(new Product("Mobile", "Sony", 100, new Branch("Hyderabad", "jack", listOfProducts), warrantyDate))));
        listOfBranches.add(new Branch("Mumbai", "jones", Arrays.asList(new Product("TV", "Sony", 1000, new Branch("Mumbai", "jones", listOfProducts), warrantyDate))));
        listOfBranches.add(new Branch("Bangalore", "michel", Arrays.asList(new Product("washing machine", "Sony", 50, new Branch("Bangalore", "Michel", listOfProducts), warrantyDate))));
        listOfBranches.add(new Branch("Delhi", "john", Arrays.asList(new Product("mobile", "Sony", 100, new Branch("Delhi", "john", listOfProducts), warrantyDate))));
        listOfBranches.add(new Branch("Noida", "ravi", Arrays.asList(new Product("Mobile", "Sony", 100, new Branch("Noida", "Ravi", listOfProducts), warrantyDate))));
        listOfBranches.add(new Branch("Gachibowli", "brendan", Arrays.asList(new Product("Mobile", "Sony", 100, new Branch("Gachibowli", "Brendan", listOfProducts), warrantyDate))));
        listOfProducts.add(new Product("Mobile", "Sony", 100, new Branch("Hyderabad", "jack", listOfProducts), warrantyDate));
        listOfProducts.add(new Product("TV", "Sony", 1000, new Branch("Mumbai", "jones", listOfProducts), warrantyDate));
        listOfProducts.add(new Product("washing machine", "Sony", 50, new Branch("Bangalore", "Michel", listOfProducts), warrantyDate));

        Role role = new Role("Branch manager", 10, "Hyderabad", Arrays.asList("english", "fundementals of business"));
        listOfEmployees.add(new Employee("jack", role, 5, "Hyderabad", 500,Arrays.asList("english", "fundementals of business","management")));
        listOfEmployees.add(new Employee("jones", role, 6, "Mumbai", 1000,Arrays.asList("English","management")));
        listOfEmployees.add(new Employee("michel", role, 7, "Bangalore", 700,Arrays.asList("english", "fundementals of business","management")));
        listOfEmployees.add(new Employee("john", role, 8, "Delhi", 800,Arrays.asList("Problem solving")));
        listOfEmployees.add(new Employee("ravi", role,9,"Noida", 900,Arrays.asList("Data Engineering","English")));
        listOfEmployees.add(new Employee("brendan", role, 10, "Gachibowli", 1200,Arrays.asList("Fundementals of business")));
        listOfRoles.add(new Role("Branch manager", 10, "Hyderabad", Arrays.asList("english", "fundementals of business","management")));
        System.out.println("Test Data Added !");
    }

    public void addProduct(String name, String brandName, int price, Branch branch, DateFormat warrantyDate) {
        Product p = new Product(name, brandName, price, branch, warrantyDate);
        listOfProducts.add(p);

    }

    public List<Product> getAllProducts() {
        return listOfProducts;
    }

    public void addBranch(String location, String managerName, List<Product> listOfP) {
        listOfBranches.add(new Branch(location, managerName, listOfP));
        listOfProducts.addAll(listOfP);
    }

    public Branch branchWithLocation(String branchLocation) {
        Branch branch = new Branch(branchLocation, "siva", listOfProducts);
        for (Branch b : listOfBranches) {
            if (Objects.equals(b.getLocation(), branchLocation)) {
                return b;
            }
        }
        return branch;
    }

    public List<Branch> getAllBranches() {
        return listOfBranches;
    }

    public void addEmployee(String name, Role roleName, int employeeExperience, String branch, int salary,List<String> listOfSkills) {
        listOfEmployees.add(new Employee(name, roleName, employeeExperience, branch, salary,listOfSkills));

    }

    public List<String> getEmployeeWithRole(Role roleName) {
        List<String> listOfEmployeeNamesWithRole = new ArrayList<>();
        for (Employee employee : listOfEmployees) {
            if (employee.getEmployeeRole() == roleName) {
                listOfEmployeeNamesWithRole.add(employee.getEmployeeName());
            }
        }
        return listOfEmployeeNamesWithRole;
    }

    public List<Employee> getAllEmployees() {
        return listOfEmployees;
    }

    public void addRole(String roleName, int experience, String location, List<String> skillsRequired) {
        listOfRoles.add(new Role(roleName, experience, location, skillsRequired));
    }

    public List<Role> getListOfRoles() {
        return listOfRoles;
    }
}




