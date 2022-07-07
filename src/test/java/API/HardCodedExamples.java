package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTcxNjU3ODIsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NzIwODk4MiwidXNlcklkIjoiMzg5NyJ9.QQAw-B3Lu3isJoHhMnrwj84j6F3yZMrSXsAOQpfSuT0";
    static String employee_id;
    @Test
    public void acreateEmployee(){
        RequestSpecification request = given().header("Content-Type","application/json").
                header("Authorization",token).body("{\n" +
                        "  \"emp_firstname\": \"Anna\",\n" +
                        "  \"emp_lastname\": \"hjfghyfg\",\n" +
                        "  \"emp_middle_name\": \"Ewa\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2000-06-12\",\n" +
                        "  \"emp_status\": \"Probation\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");

        Response response = request.when().post("/createEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);
        // hamcrest matchers
        response.then().assertThat().body("Message",equalTo("Employee Created"));
        response.then().assertThat().body("Employee.emp_firstname",equalTo("Anna"));

        //using jsonPath() to specify the key in the body so it will return the value against it
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }
    @Test
    public void bgetCreatedEmployee(){
        RequestSpecification request = given().header("Content-Type","application/json").
                header("Authorization", token).queryParam("employee_id",employee_id);
        Response response = request.when().get("getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        String tempId = response.jsonPath().getString("employee.employee_id");
        System.out.println(employee_id);
        Assert.assertEquals(tempId,employee_id);

    }
    @Test
    public void cupdateEmployee(){
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization",token).
                body("{\n" +
                        "  \"employee_id\": \"" + employee_id+ "\",\n" +
                        "  \"emp_firstname\": \"Annnnna\",\n" +
                        "  \"emp_lastname\": \"Nowak\",\n" +
                        "  \"emp_middle_name\": \"Jola\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2000-06-12\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"Manager\"\n" +
                        "}\n");
        Response response = request.when().put("updateEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void dGetUpdatedEmployee(){
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization",token).queryParam("employee_id",employee_id);

        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void eGetAllEmployees(){
        RequestSpecification request = given().header("Content-Type","application/json").
                header("Authorization",token);
        Response response = request.when().get("/getAllEmployees.php");
        String alleEmployees = response.prettyPrint();

        //jsonPath () vs jsonPath class
        //class contains method that converts the values into json object
        //jsonPath() is a method that belongs to jsonPath class

        //creating an object of jsonPath class
        JsonPath js = new JsonPath(alleEmployees);

        //retrieving the total number of employees
        int count = js.getInt("Employees.size()");
        System.out.println(count);

        // to print only employee id of all employees
        for (int i=0; i<count; i++){
            String empId = js.getString("Employees["+i+"].employee_id");
            System.out.println(empId);
        }
    }
}
