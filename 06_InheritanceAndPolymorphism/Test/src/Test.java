import java.util.ArrayList;
import java.util.Iterator;

public class Test {
        public int id;
        public String name;

        public Test(int id, String name) {
            this.id = id;
            this.name = name;
        }




        @Override
        public String toString() {
            return "Test{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        //Start !!!

        public static void main(String[] args) {

            ArrayList<Test> test = new ArrayList<>();
            test.add(new Test(1,"Tom"));
            test.add(new Test(2,"Bob"));
            test.add(new Test(3,"Alex"));
            test.add(new Test(4,"Marta"));
            test.add(new Test(5,"John"));
            test.add(new Test(6,"Oscar"));
            System.out.println(test.size());
            System.out.println(test.get(1).id);
            Iterator<Test> testIterator = test.iterator();
            while(testIterator.hasNext()) {
                Test nextTest = testIterator.next();
                if (nextTest.name.equals("Jhon")) {
                    testIterator.remove();
                }
            }

            System.out.println(test.size());
            System.out.println(test);
        }
}
