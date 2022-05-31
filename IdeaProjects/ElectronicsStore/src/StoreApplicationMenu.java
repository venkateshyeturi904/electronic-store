import model.*;
import service.StoreService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
public class StoreApplicationMenu {
    StoreService storeService = new StoreService();
    public StoreApplicationMenu() throws ParseException {
        System.out.println("Welcome to StoreManagerApplication");
        System.out.println("-----------------------------------------");
        System.out.println("Add test data for smoother experience with the application !");
        int input =0;
        while(input!=12) {
            System.out.println("=============================================");
            System.out.println("Main Menu:");
            System.out.println("=============================================");
            System.out.println("1. Add Product in store ");
            System.out.println("2. See all available products");
            System.out.println("3. Add Branches ");
            System.out.println("4. See all Branches");
            System.out.println("5. Add Employee");
            System.out.println("6. See all employees");
            System.out.println("7. Add Role");
            System.out.println("8. See all roles");
            System.out.println("9. Back to menu");
            System.out.println("10. Add Test data");
            System.out.println("11. Advanced Menu");
            System.out.println("12. Exit");
            System.out.println("=============================================");
            System.out.println("Enter value 1-12");
            input=takeIntInput();
            switch(input){
                case 1 : addingProduct();
                         break;


                case 2 : printAllProducts(storeService.getAllProducts());
                         break;

                case 3 : addingBranch();
                         break;

                case 4 : printAllBranches(storeService.getAllBranches());
                         break;

                case 5 : addingEmployee();
                         break;

                case 6 : printAllEmployees(storeService.getAllEmployees());
                         break;

                case 7 : addingRole();
                         break;

                case 8 : printAllRoles();
                         break;

                case 9 :
                         break;

                case 10 : addingTestData();
                          break;

                case 11 : advanceMenu();
                          break;

                case 12: System.exit(0);

                default : System.out.println("Enter valid input 1-12");

            }
        }
    }

    public void mainMenu(){

    }

    public void advanceMenu() throws ParseException {
        int input =0;

        while(input!=7) {

            System.out.println("=============================================");
            System.out.println("Advanced Menu:");
            System.out.println("=============================================");
            System.out.println("1. Get employees with salary greater than...");
            System.out.println("2. Get products in a praticular branch");
            System.out.println("3. See vacancies in roles ");
            System.out.println("4. See employees with less than required skills and experience");
            System.out.println("5. Generate employee FitScore");
            System.out.println("6. Back to main menu");
            System.out.println("7. Exit");
            System.out.println("=============================================");
            System.out.println("Enter any value 1-6");
            input = takeIntInput();
            switch (input) {
                case 1:
                    gettingEmployeesWithSalaryGreaterThan();
                    break;
                case 2:
                    gettingProductsInAParticularBranch();
                    break;
                case 3:
                    seeingVacanciesInRoles();
                    break;
                case 4:
                    seeingEmployeesWithLessThanRequiredSkillsAndExperience();
                    break;
                case 5:
                    generatingFitScore();
                    break;
                case 6:
                    StoreApplicationMenu storeApplicationMenu = new StoreApplicationMenu();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter a valid input between 1-6");
            }
        }
    }
    public void addingProduct() throws ParseException {
        System.out.println("Enter the type of the product  (Eg: mobile , TV)");
        String typeOfProduct = takeStringInput();
        System.out.println("Enter the brand name  (Eg : Apple, Samsung)");
        String brandName = takeStringInput();
        System.out.println("Enter the price set for the product in $");
        int price = takeIntInput();
        System.out.println("The list of branches available are: ");
        printAllBranches(storeService.getAllBranches());
        System.out.println("Enter the branch location in which this ");
        Scanner sc = new Scanner(System.in);
        String branchLocation =sc.nextLine();
        if(!storeService.getAllBranches().stream().map(Branch::getLocation).filter(x->x.equalsIgnoreCase(branchLocation)).collect(Collectors.toList()).isEmpty()){
            Branch branchTemporary = storeService.branchWithLocation(branchLocation);
            Branch branch = new Branch(branchLocation,branchTemporary.getBranchManager(),branchTemporary.getProducts());
            System.out.println("Enter the warranty validity date of the product in dd/mm/yyyy format");
            DateFormat warrantyDate = new SimpleDateFormat("dd/MM/yyyy");
            warrantyDate.parse(takeStringInput());
            warrantyDate.setLenient(false);
            storeService.addProduct(typeOfProduct, brandName, price,branch, warrantyDate);
            System.out.println("Product added");
        } else{
            System.out.println("Specified branch does not exist in our business! ");
        }
    }
    public void printAllProducts(List<Product> list){
        if(storeService.getAllProducts().isEmpty()){
            System.out.println("No Products added yet");
        }else {
            System.out.println("All the products available are :");
            int i=0,count=0;
            String YorN = "y";
            int size = list.size();
            if(size<=5){
                for (Product p : list) {
                    System.out.println(++count +". "+p.toString());
                }
            }else{
                while(!YorN.equalsIgnoreCase("n")) {
                    for (; i < 5 + count; i++) {
                        try {
                            System.out.println(++count  +". "+list.get(i).toString());
                        }catch(Exception ignored){
                        }
                    }
                    count=(count<=size)?count+5:size-5;
                    if(i<size) {
                        System.out.println("Show more (y/n) ? ->");
                        Scanner scanYorN = new Scanner(System.in);
                        YorN=takeYesOrNoInput();
                    }
                    else{
                        YorN = "n";
                    }
                }
            }
        }
    }
    public void addingBranch() throws ParseException {
        System.out.println("List of branches we have are :");
        printAllBranches(storeService.getAllBranches());
        System.out.println("Enter the location (Eg: Hyd , Mumbai)");
        String location = takeStringInput();
        if(!storeService.getAllBranches().stream().map(Branch::getLocation).filter(x->x.equalsIgnoreCase(location)).collect(Collectors.toList()).isEmpty()){
            System.out.println("our branch already exists here :) ");
        }else {
            System.out.println("Enter the name of branch manager");
            System.out.println("List of currently available employees are :");
            for(Employee e : storeService.getAllEmployees()){
                System.out.println(e.getEmployeeName());
            }
            System.out.println("\n");
            String nameOfManager = takeStringInput();
            if (storeService.getAllEmployees().stream().map(Employee::getEmployeeName).collect(Collectors.toList()).contains(nameOfManager)) {
                System.out.println("Enter the list of products to be available in this branch");
                System.out.println("---------");
                List<Product> listOfProductsInThisBranch = new ArrayList<>();
                System.out.println("Enter the type of the product  (Eg: mobile , TV)");
                String typeOfProduct = takeStringInput();
                System.out.println("Enter the brand name  (Eg : Apple, Samsung)");
                String brandName = takeStringInput();
                System.out.println("Enter the price set for the product in $");
                int price = takeIntInput();
                System.out.println("Enter the warranty validity date of the product in dd/mm/yyyy format");
                DateFormat warrantyDate = new SimpleDateFormat("dd/MM/yyyy");
                warrantyDate.parse(takeStringInput());
                warrantyDate.setLenient(false);
                Product product = new Product(typeOfProduct, brandName, price, new Branch(location, nameOfManager, listOfProductsInThisBranch), warrantyDate);
                listOfProductsInThisBranch.add(product);
                storeService.addBranch(location.toLowerCase(), nameOfManager, listOfProductsInThisBranch);
                System.out.println(location + " Branch added");
            }else {
                System.out.println("Employee with this name is not present");
                System.out.println("=========================================");
                System.out.println("Do you want to add a new employee ? (y/n)");
                if(takeStringInput()=="y"){
                    addingEmployee();
                    addingBranch();
                }
            }
        }
    }
    public void printAllBranches(List<Branch> list ){
        if(list.isEmpty()){
            System.out.println("No branches currently");
        }
        else {
            System.out.println("List of branches :");
            int i=0,count=0;
            String YorN = "y";
            int size = list.size();
            if(size<=5) {
                for (Branch b : list) {
                    System.out.println(b.getLocation());
                }
            }else{
                while(Objects.equals(YorN, "y") || Objects.equals(YorN, "Y")) {
                    for (; i < 5 + count; i++) {
                        try {
                            System.out.println(list.get(i).getLocation());
                        }catch(Exception ignored){
                        }
                    }
                    count=(count+5<=size)?count+5:size-5;
                    if(i<size) {
                        System.out.println("Show more (y/n) ? ->");
                        YorN=takeYesOrNoInput();
                    }
                    else{
                        YorN="n";
                    }

                }
            }
        }
    }
    public void addingEmployee(){
        System.out.println("Enter the name of the employee");
        String name = takeStringInput();
        System.out.println("Enter the role Eg: Branch manager / Sales manager");
        System.out.println("List of available roles are :");
        storeService.getListOfRoles().stream().map(Role::getRoleName).forEach(System.out::println);
        System.out.println("\n");
        String roleName = takeStringInput();
        Role roleTemporary=null;
        if(!storeService.getListOfRoles().stream().map(Role::getRoleName).filter(x->x.equalsIgnoreCase(roleName)).collect(Collectors.toList()).isEmpty()) {
            for(Role role : storeService.getListOfRoles()){
                if(role.getRoleName().equalsIgnoreCase(roleName)){
                    roleTemporary=role;
                }
            }
            Role role = new Role(roleName,roleTemporary.getExperience(),roleTemporary.getLocation(),roleTemporary.getSkillsRequired());
            System.out.println("Enter the experience of this employee");
            int employeeExperience = takeIntInput();
            int roleExperience = role.getExperience();
            String yesOrNo="y";
            while(Objects.equals(yesOrNo, "y")) {
                if (employeeExperience >= roleExperience) {
                    System.out.println("Available branches are :");
                    printAllBranches(storeService.getAllBranches());
                    System.out.println("Enter respective branch ");
                    String branch = takeStringInput();
                    if(!storeService.getAllBranches().stream().map(Branch::getLocation).filter(x->x.equalsIgnoreCase(branch)).collect(Collectors.toList()).isEmpty()) {
                        System.out.println("Enter salary in $");
                        int salary = takeIntInput();
                        System.out.println("Enter the skillSet of this employee each seperated by comma");
                        String skillSet = takeStringInput();
                        List<String> listOfSkills = List.of(skillSet.split(","));
                        storeService.addEmployee(name, role, employeeExperience, branch, salary, listOfSkills);
                        System.out.println("Employee added");
                        yesOrNo = "n";
                    }else {
                        System.out.println("Please select existing branch only !");
                    }
                } else {
                    System.out.println("Employee's experience is less than the required ! still do you want to proceed ?(y/n)");
                    roleExperience=0;
                    yesOrNo=takeStringInput();
                }
            }
        }else {
            System.out.println("Specified role does not exist! So add this role in our store server and then add this employee ");
        }
    }
    public void printAllEmployees(List<Employee> list){
        if(list.isEmpty()){
            System.out.println("No employees yet");
        }else {
            System.out.println("All of our employees are :");
            int i=0,count=0;
            String YorN = "y";
            int size = list.size();
            if(size<=5) {
                for (Employee e : list) {
                    System.out.print(e.getEmployeeName() + "-> "+e.getEmployeeRole()+", "+e.getEmployeeBranch()+" branch");
                }
            }else{
                while(!YorN.equalsIgnoreCase("n")) {
                    for (; i < 5 + count; i++) {
                        try {
                            System.out.println(list.get(i).getEmployeeName() + "-> "+list.get(i).getEmployeeRole().getRoleName()+", "+list.get(i).getEmployeeBranch()+" branch");
                        }catch(Exception ignored){
                        }
                    }
                    count=(count<=size)?count+5:size-5;
                    if(i<size) {
                        System.out.println("Show more (y/n) ? ->");
                        YorN=takeYesOrNoInput();
                    }
                    else{
                        YorN = "n";
                    }
                }
            }
        }
    }
    public void addingRole(){
        System.out.println("Enter the role Eg: sales manager/ branch manager");
        String name = takeStringInput();
        System.out.println("Enter the set experience required for this role");
        int experience = takeIntInput();
        System.out.println("Available branches are : ");
        printAllBranches(storeService.getAllBranches());
        System.out.println("Enter the location for this role");
        String location = takeStringInput();
        if(!storeService.getAllBranches().stream().map(Branch::getLocation).filter(x->x.equalsIgnoreCase(location)).collect(Collectors.toList()).isEmpty()) {
            System.out.println("Enter the skills required for this role each one seperated by ,");
            Scanner s4 = new Scanner(System.in);
            List<String> skillsRequired = new ArrayList<>(Arrays.asList(s4.next().split(",")));
            storeService.addRole(name, experience, location, skillsRequired);
            System.out.println("Role added");
        }else{
            System.out.println("Specified branch does not exist in our business");
        }
    }
    public void printAllRoles(){
        int count=0;
        if(storeService.getListOfRoles().isEmpty()){
            System.out.println("No roles added yet");
        }else {
            for (Role r : storeService.getListOfRoles()) {
                System.out.println(++count + "."+r.getRoleName());
            }
        }
    }
    public void addingTestData() throws ParseException {
        if(storeService.getAllBranches().isEmpty()) {
            storeService.addTestData();
        }else{
            System.out.println("Test data can be added at the beginning only");
        }
    }
    public void gettingEmployeesWithSalaryGreaterThan(){
        System.out.println("Enter the salary for comparing");
        int salary = takeIntInput();
        List<Employee> list = storeService.getAllEmployees().stream().filter(x->x.getEmployeeSalary()>salary).collect(Collectors.toList());
        if(list.isEmpty()){
            System.out.println("No employees with salary greater than "+salary+"$");
        }else{
            for(Employee e : list){
                System.out.println(e.getEmployeeName()+" -> "+e.getEmployeeRole().getRoleName()+" , "+e.getEmployeeSalary()+"$");
            }
        }
    }
    public void gettingProductsInAParticularBranch(){
        System.out.println("List of available branches are : ");
        printAllBranches(storeService.getAllBranches());
        System.out.println("Enter the branch location");
        String branch = takeStringInput();
        List<String> listOfProducts = storeService.getAllProducts().stream().filter(x->x.getBranch().getLocation().equalsIgnoreCase(branch)).map(Product::getName).collect(Collectors.toList());
        if(listOfProducts.isEmpty()){
            System.out.println("No products available");
        }else {
            listOfProducts.stream().forEach(System.out::println);
        }
    }
    public void seeingVacanciesInRoles(){

    }
    public void seeingEmployeesWithLessThanRequiredSkillsAndExperience(){
        System.out.println("Employees with skills less than required for that particular role");
        for(Employee e : storeService.getAllEmployees()){
            if(countOfMatchingSkills(e)<e.getEmployeeRole().getSkillsRequired().size()){
                System.out.println(e.getEmployeeName()+" with skills-> "+e.getEmployeeSkills()+" but required skills: "+e.getEmployeeRole().getSkillsRequired());
            }
        }
    }
    public void generatingFitScore(){
        int fitScore =0;
        System.out.println("List of available employees are are:");
        printAllEmployees(storeService.getAllEmployees());
        System.out.println("Enter the employee name");
        String name = takeStringInput();
        if(!storeService.getAllEmployees().stream().map(Employee::getEmployeeName).filter(x->x.equalsIgnoreCase(name)).collect(Collectors.toList()).isEmpty()) {
            Employee e = storeService.getAllEmployees().stream().filter(x -> x.getEmployeeName().equalsIgnoreCase(name)).findAny().get();
            fitScore = 3 * (e.getEmployeeExperience() / e.getEmployeeRole().getExperience()) + 7 * (countOfMatchingSkills(e) / e.getEmployeeRole().getSkillsRequired().size());
            System.out.println("Fit Score is : "+fitScore +"   ( /10)");
            System.out.println("------Note : This FitScore is generated based on employee experience and skillset------");
        }
        else {
            System.out.println("Enter existing employee name only !\n");
            generatingFitScore();
        }
        System.out.println("Do you want to see another employee's fitscore ? (y/n)");
        String YorN = takeYesOrNoInput();
        if(YorN.equalsIgnoreCase("y")){
            generatingFitScore();
        }
    }

    public int countOfMatchingSkills(Employee e){
        int count=0;
        for(String s : e.getEmployeeRole().getSkillsRequired() ){
            if(e.getEmployeeSkills().contains(s)){
                count++;
            }
        }
        return count;
    }

    public String takeStringInput(){
        Scanner scanString = new Scanner(System.in);
        String input = null;
        try {
            input = scanString.nextLine();

        }catch(Exception e){
            System.out.println("Enter valid Input");
            input = takeStringInput();
        }
        return input;
    }
    public int takeIntInput(){
        Scanner scanInt = new Scanner(System.in);
        int input =0;
        try{
            input = scanInt.nextInt();
        }
        catch(Exception e){
            System.out.println("Enter valid Input");
            input = takeIntInput();
        }
        return input;
    }

    public String takeYesOrNoInput(){
        Scanner scanYOrN = new Scanner(System.in);
        String input = scanYOrN.next();
        if(input.equalsIgnoreCase("y")||input.equalsIgnoreCase("n")){

        }else {
            System.out.println("Enter valid input y or n for yes or no");
            input=takeYesOrNoInput();
        }
        return input;
    }
}
