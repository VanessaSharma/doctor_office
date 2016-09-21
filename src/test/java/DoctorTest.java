import org.sql2o.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;


public class DoctorTest {

  @Before
  public void initialize() {
      DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doctor_office_test", null, null);
      Doctor myDoctor = new Doctor("Doctor House", "Allergist");
    }

    @After
    public void tearDown() {
      try(Connection con = DB.sql2o.open()) {
        String deletePatientsQuery = "DELETE FROM patients *;";
        String deleteDoctorsQuery = "DELETE FROM doctors *;";
        con.createQuery(deletePatientsQuery).executeUpdate();
        con.createQuery(deleteDoctorsQuery).executeUpdate();
      }
    }

    @Test
    public void doctor_instantiatesCorrectly() {
      Doctor myDoctor = new Doctor("Doctor House", "Allergist");
      assertTrue(myDoctor instanceof Doctor);
    }

    @Test
    public void getName_doctorInstantiatesWithName_Doc() {
      Doctor myDoctor = new Doctor("Doctor House", "Allergist");
      assertEquals("Doctor House", myDoctor.getName());
    }

    @Test
    public void getSpecialty_doctorInstantiatesWithSpecialty_Allergist() {
      Doctor myDoctor = new Doctor("Doctor House", "Allergist");
      assertEquals("Allergist", myDoctor.getSpecialty());
    }

    // @Test
    // public void getId_doctorInstantiatesWithId_1() {
    //   myDoctor.save();
    //   assertTrue(myDoctor.getId() > 0);
    // }
}
