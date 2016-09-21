import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class PatientTest {

  @Before
  public void initialize() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doctor_office_test", null, null);
    Patient myPatient = new Patient("Vanessa Palacios", "03/29/1991",1);
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
  public void patient_instantiatesCorrectly_true() {
    Patient myPatient = new Patient("Vanessa Palacios", "03/29/1991", 1);
    assertEquals(true, myPatient instanceof Patient);
  }

  @Test
  public void getName_instantiatesWithName_String() {
    Patient myPatient = new Patient("Vanessa Palacios", "03/29/1991", 1);    assertEquals("Vanessa Palacios", myPatient.getName());
  }

  @Test
  public void getBirthday_instantiatesWithBirthday_String() {
    Patient myPatient = new Patient("Vanessa Palacios", "03/29/1991", 1);    assertEquals("03/29/1991", myPatient.getBirthday());
  }

  @Test
  public void getPatitentId_PatientInstantiatesWithId_1() {
    Patient myPatient = new Patient("Vanessa Palacios", "03/29/1991", 1);  assertEquals(0, myPatient.getId());
  }

  @Test
  public void save_assignsIdToObject() {
    Patient myPatient = new Patient("Patient House", "03/29/1991", 1);
    myPatient.save();
    Patient savedPatient = Patient.all().get(0);
    assertEquals(myPatient.getId(), savedPatient.getId());
  }
}
