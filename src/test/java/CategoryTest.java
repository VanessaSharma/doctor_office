// import org.junit.*;
// import static org.junit.Assert.*;
// import org.sql2o.*;
// import java.util.Arrays;
//
//
//
// public class CategoryTest {
//   private Category firstCategory;
//   private Category secondCategory;
//   private Task testTask;
//
//   @Before
//   public void setUp() {
//     DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", null, null);
//   }
//
//   @After
//   public void tearDown() {
//     try(Connection con = DB.sql2o.open()) {
//       String deleteTasksQuery = "DELETE FROM tasks *;";
//       String deleteCategoriesQuery = "DELETE FROM categories *;";
//       con.createQuery(deleteTasksQuery).executeUpdate();
//       con.createQuery(deleteCategoriesQuery).executeUpdate();
//     }
//   }
//
//   @Before
//   public void initialize() {
//     firstCategory = new Category("Inside");
//     secondCategory = new Category("Outside");
//     testTask = new Task("Make bed");
//   }
//
//   @Test
//   public void getTasks_retrievesALlTasksFromDatabase_tasksList() {
//     Category myCategory = new Category("Household chores");
//     myCategory.save();
//     Task firstTask = new Task("Mow the lawn", myCategory.getId());
//     firstTask.save();
//     Task secondTask = new Task("Do the dishes", myCategory.getId());
//     secondTask.save();
//     Task[] tasks = new Task[] { firstTask, secondTask };
//     assertTrue(myCategory.getTasks().containsAll(Arrays.asList(tasks)));
//   }
//   @Test
//   public void save_savesIntoDatabase_true() {
//     Category myCategory = new Category("Household chores");
//     myCategory.save();
//     assertTrue(Category.all().get(0).equals(myCategory));
//   }
//
//   @Test
//   public void all_returnsAllInstancesOfCategory_true() {
//     Category firstCategory = new Category("Home");
//     firstCategory.save();
//     Category secondCategory = new Category("Work");
//     secondCategory.save();
//     assertEquals(true, Category.all().get(0).equals(firstCategory));
//     assertEquals(true, Category.all().get(1).equals(secondCategory));
//   }
//
//   @Test
//   public void save_assignsIdToObject() {
//     Category myCategory = new Category("Household chores");
//     myCategory.save();
//     Category savedCategory = Category.all().get(0);
//     assertEquals(myCategory.getId(), savedCategory.getId());
//   }
//
//   @Test
//   public void Category_instantiatesCorrectly_true() {
//     assertTrue(firstCategory instanceof Category);
//   }
//
//   @Test
//   public void getName_categoryInstantiatesWithName_Inside() {
//     assertEquals("Inside", firstCategory.getName());
//   }
//
//   @Test
//   public void all_checksIfCategoryListContainsInstance_true() {
//     assertTrue(Category.all().contains(firstCategory));
//     assertTrue(Category.all().contains(secondCategory));
//    }
//
//    @Test
//    public void getId_categoriesInstantiateWithAnId_1() {
//      Category testCategory = new Category("Home");
//      testCategory.save();
//      assertTrue(testCategory.getId() > 0);
//    }
//
//   @Test
//   public void clear_clearsCategoryList_0() {
//     assertEquals(Category.all().size(), 0);
//   }
//
//   @Test
//   public void addTask_addsTasksToCategoryList_true() {
//     firstCategory.addTask(testTask);
//     assertTrue(firstCategory.getTasks().contains(testTask));
//   }
//
//   @Test
//   public void find_returnsCategoryWithSameId_secondCategory() {
//     Category firstCategory = new Category("Home");
//     firstCategory.save();
//     Category secondCategory = new Category("Work");
//     secondCategory.save();
//     assertEquals(Category.find(secondCategory.getId()), secondCategory);
//   }
// }
