// import org.junit.*;
// import static org.junit.Assert.*;
// import java.time.LocalDateTime;
//
// public class TaskTest {
//   private Task firstTask;
//   private Task secondTask;
//
//   @Before
//   public void setUp() {
//     DB.sql2o = new Sql2o ("jdbc:postgresql://localhost:5432?to_do_test", null, null);
//     //creates a new instance of Sql2o ..... specify the to_do_test database not to_do;
//   }
//
//   @After
//   public void tearDown() {
//     //This is known as try with resources It will safely close the connection after the code in its block is executed
//     try(Connection con = DB.sql2o.open()) {
// //con = this object represents our active connection to the database
//       String sql = "DELETE FROM tasks *;";
//       //This command instructs our database to delete everything from the tasks table
//       con.createQuery(sql).executeUpdate();
//       //use our Connection to create a query.. pass in the sql statement we've just constructed
//       //executeUpdate() on our new SQL query, which passes our sql command to the database
//     }
//   }
//
//   @Test
//   public void save_savesCategoryIdIntoDB_true() {
//     Category myCategory = new Category("Household chores");
//     myCategory.save();
//     Task myTask = new Task("Mow the lawn", myCategory.getId());
//     myTask.save();
//     //create a new Category and Task
//     //In the Task constructor we use myCategory.getId() to reference an actual Category id saved in our database
//     Task savedTask = Task.find(myTask.getId());
//     assertEquals(savedTask.getCategoryId(), myCategory.getId());
//     //we find our new Task and compare its categoryId with myCategory.getId() to ensure it correctly contains the ID for the Category it belongs to
//   }
//
//   @Test
//   public void find_returnsTaskWithSameId_secondTask() {
//     Task firstTask = new Task("Mow the lawn");
//     firstTask.save();
//     Task secondTask = new Task("Buy groceries");
//     secondTask.save();
//     assertEquals(Task.find(secondTask.getId()), secondTask);
//   }
//
//   @Test
//   public void update_updatesTaskDescription_true() {
//     Task myTask = new Task("Mow the lawn", 1);
//     myTask.save();
//     myTask.update("Take a nap");
//     assertEquals("Take a nap", Task.find(myTask.getId()).getDescription());
//   }
//
//   @Test
//   public void save_assignsIdToObject() {
//     Task myTask = new Task("Mow the lawn");
//     myTask.save();
//     Task savedTask = Task.all().get(0);
//     assertEquals(myTask.getId(), savedTask.getId());
//   }
//
//
//   @Test
//   public void save_returnsTrueIfDescriptionsAretheSame() {
//     Task myTask = new Task("Mow the lawn");
//     myTask.save();
//     assertTrue(Task.all().get(0).equals(myTask));
//   }
//
//   @Test
//    public void Task_instantiatesCorrectly_true() {
//     assertEquals(true, firstTask instanceof Task);
//   }
//
//   @Test
//   public void Task_instantiatesWithDescription_String() {
//     assertEquals("Mow the lawn", firstTask.getDescription());
//   }
//
//   @Test
//   public void isCompleted_isFalseAfterInstantiation_false() {
//     assertFalse(firstTask.isCompleted());
//   }
//
//   @Test
//   public void getCreatedAt_instantiateWithCurrentTime_today() {
//     assertEquals(LocalDateTime.now().getDayOfWeek(), firstTask.getCreatedAt().getDayOfWeek());
//   }
//
//   @Test
//   public void all_returnsAllInstancesOfTask_true() {
//     assertTrue(Task.all().contains(firstTask));
//     assertTrue(Task.all().contains(secondTask));
//   }
//
//
//   @Test
//   public void getId_tasksInstantiateWithAnID_1() {
//     Task myTask = new Task("Mow the lawn");
//     myTask.save();
//     assertTrue(myTask.getId() > 0);  }
//
//   @Test
//   public void find_returnsTaskWithSameId_secondTask() {
//     assertEquals(Task.find(secondTask.getID()), secondTask);
//   }
// }
