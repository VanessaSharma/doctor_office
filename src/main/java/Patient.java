import org.sql2o.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Patient {
  private String name;
  private String birthday;
  private int id;
  private int doctorId;

  public Patient(String name, String birthday, int doctorId) {
    this.name = name;
    this.birthday = birthday;
    this.doctorId = doctorId;
  }

  public String getName() {
    return name;
  }

  public String getBirthday() {
    return birthday;
  }

  public int getId() {
    return id;
  }

  public static Patient find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients WHERE id = :id";
      Patient  patient= con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patient.class);
      return patient;
    }
  }

  public static List<Patient> all() {
    String sql = "SELECT id, name, birthday FROM patients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patient.class);
    }
  }
  public int getDoctorId() {
    return doctorId;
  }

  @Override
  public boolean equals(Object otherPatient) {
    if (!(otherPatient instanceof Patient)) {
      return false;
    } else {
      Patient newPatient = (Patient) otherPatient;
      return this.getName().equals(newPatient.getName()) && this.getBirthday().equals(newPatient.getBirthday()) && this.getId() == newPatient.getId() && this.getDoctorId() == newPatient.getDoctorId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients (name, birthday) VALUES (:name, :birthday)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("birthday", this.birthday)
        .executeUpdate()
        .getKey();
    }
  }
  public void update(String name, String birthday) {
   try(Connection con = DB.sql2o.open()) {
     String sql = "UPDATE patients SET name, birthday = :name, :birthday  WHERE id = :id";
     con.createQuery(sql)
       .addParameter("name", name)
       .addParameter("birthday", birthday)
       .addParameter("id", id)
       .executeUpdate();
     }
   }
  public void delete() {
  try(Connection con = DB.sql2o.open()) {
  String sql = "DELETE FROM patients WHERE id = :id;";
  con.createQuery(sql)
    .addParameter("id", id)
    .executeUpdate();
    }
  }
}
