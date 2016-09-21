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
   model.put("doctors", Doctor.all());
   model.put("template", "template/index.vtl");
   return new ModelAndView(model, layout);
 }, new VelocityTemplateEngine());

get("doctors/:id/patients/new", (request, response)-> {
  Map<String, Object> model = new HashMap<String, Object>();
  Doctor doctor = Doctor.find(Integer.parseInt(request.params(":id")));
  model.put("doctors", doctor);
  model.put("template", "template/doctor-patients-form.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

}
}
