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

  get("doctors/:doctor_id/patients/:id", (request, response)-> {
    Map<String, Object> model = new HashMap<String, Object>();
    Doctor doctor = Doctor.find(Integer.parseInt(request.params(":doctor_id")));
    Patient patient = Patient.find(Integer.parseInt(request.params(":id")));
    model.put("doctor", doctor);
    model.put("patient", patient);
    model.put("template", "templates/patient.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/doctors/:doctor_id/patients/:id", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Patient patient = Patient.find(Integer.parseInt(request.params("id")));
    String name = request.queryParams("name");
    String specialty = request.queryParams("specialty");
    Doctor doctor = Doctor.find(patient.getDoctorId());
    patient.update(name, specialty);
    String url = String.format("/doctors/%d/patients/%d", doctor.getId(), patient.getId());
    response.redirect(url);
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

  get("/patients/:id", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Patient patient = Patient.find(Integer.parseInt(request.params(":id")));
    model.put("patient", patient);
    model.put("template", "templates/patient.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/doctors", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    String name = request.queryParams("name");
    String specialty = request.queryParams("specialty");
    Doctor newDoctor = new Doctor(name, specialty);
    newDoctor.save();
    model.put("template", "templates/doctor-success.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/patients", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("patients", Patient.all());
    model.put("template", "templates/patients.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/doctors/new", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("template", "templates/doctor-form.vtl");
  return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/doctors", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("doctors", Doctor.all());
    model.put("template", "templates/doctors.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/doctors/:id", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Doctor doctor = Doctor.find(Integer.parseInt(request.params(":id")));
    model.put("doctor", doctor);
    model.put("template", "templates/doctor.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/doctors/:doctor_id/patients/:id/delete", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Patient patient = Patient.find(Integer.parseInt(request.params("id")));
    Doctor doctor = Doctor.find(patient.getDoctorId());
    patient.delete();
    model.put("doctor", doctor);
    model.put("template", "templates/doctor.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  }
}
