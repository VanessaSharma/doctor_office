import org.sql2o.*;
//Imported the sql20 library we added to build.gradle


public class DB {
  public static Sql2o sql2o = new Sql2o("jdbc:postgres://localhost:5432/doctor_office", null, null);
  //        URL Java uses to access the database..find our server at localhost: 5432
}
