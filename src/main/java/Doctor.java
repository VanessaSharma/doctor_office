import org.sql2o.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;


public class Doctor {
  private String name;
  private String specialty;
  private int id;

  public Doctor(String name, String specialty) {
    this.name = name;
    this.specialty = specialty;
    }

  public String getName() {
    return name;
  }

  public String getSpecialty() {
    return specialty;
  }

  public int getId() {
    return id;
  }

  public static Doctor find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM doctors WHERE id=:id";
      Doctor doctor = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Doctor.class);
    return doctor;
    }
  }

  public static List<Doctor> all() {
    String sql = "SELECT * id, name, specialty FROM doctors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Doctor.class);
    }
  }
 public void save() {
   try(Connection con = DB.sql2o.open()){
     String sql = "INSERT INTO doctors(specialty, name) VALUES (:specialty, :name)";
     this.id = (int) con.createQuery(sql, true)
     .addParameter("specialty", this.specialty)
     .addParameter("name", this.name)
     .executeUpdate()
     .getKey();
   }
 }
  @Override
  public boolean equals(Object otherDoctor) {
    if (!(otherDoctor instanceof Doctor)) {
      return false;
    } else {
      Doctor newDoctor = (Doctor) otherDoctor;
      return this.name.equals(newDoctor.name) && this.specialty.equals(newDoctor.specialty);
    }
  }
}
