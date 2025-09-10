package comtest;

import com.Student;
import org.junit.*;
import java.util.*;

public class D34t4 {

    static HashMap<Integer, Student> map;

    @BeforeClass
    public static void setUpBeforeClass() {
        map = new HashMap<>();
        map.put(1, new Student(1, "Arjun", "10A", 15, 80, 75, 90));
        map.put(2, new Student(2, "Meera", "10B", 16, 85, 88, 92));
        System.out.println("BeforeClass: Test data initialized");
    }

    @AfterClass
    public static void tearDownAfterClass() {
        map.clear();
        System.out.println("AfterClass: Test data cleared");
    }

    @Before
    public void setUp() {
        System.out.println("Before: Starting new test");
    }

    @After
    public void tearDown() {
        System.out.println("After: Test finished");
    }

    @Test
    public void testAggregateCalculation() {
        Student s = map.get(1);
        Assert.assertEquals(81.66, s.aggregate, 0.1);  // double compare with delta
    }

    @Test
    public void testUpdateMarks() {
        Student s = map.get(2);
        s.updateMarks(90, 90, 90);
        Assert.assertEquals(90.0, s.aggregate, 0.01);
    }

    @Test
    public void testAddStudent() {
        Student s = new Student(3, "Ravi", "10C", 15, 70, 80, 90);
        map.put(s.id, s);
        Assert.assertTrue(map.containsKey(3));
        Assert.assertNotNull(map.get(3));
    }

    @Test
    public void testTopStudent() {
        Student top = null;
        for (Student s : map.values()) {
            if (top == null || s.aggregate > top.aggregate) top = s;
        }
        Assert.assertEquals("Meera", top.name);
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidStudentAccess() {
        Student s = map.get(99);  // doesn’t exist
        s.updateMarks(50, 50, 50); // should throw NullPointerException
    }
}
