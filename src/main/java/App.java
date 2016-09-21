import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

   get("/", (request, response) -> {
     Map<String, Object> model = new HashMap<String, Object>();
     model.put("template", "templates/index.vtl");
     model.put("doctors", Doctor.all());
     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

  get("doctors/:id/patients/new", (request, response)-> {
    Map<String, Object> model = new HashMap<String, Object>();
    Doctor doctor = Doctor.find(Integer.parseInt(request.params(":id")));
    model.put("doctors", doctor);
    model.put("template", "templates/doctor-patients-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/patients", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("patients", Patient.all());
    model.put("template", "templates/patients.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/patients", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Doctor doctor = Doctor.find(Integer.parseInt(request.queryParams("doctorId")));
        String name = request.queryParams("name");
        String birthday = request.queryParams("birthday");
        Patient newPatient = new Patient(name, birthday, doctor.getId());
        newPatient.save();
        model.put("doctor", doctor);
        model.put("template", "templates/doctor-patients-form-success.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());
  }
}
