import java.util.ArrayList;
import java.util.List;

public class Main {

    public interface Test<T, I> {
        //Insert
        public void insert(T t);

        //Delete
        public void delete(T t);

        //Update
        public void update(T t);

        //GetAll
        public List<T> getAll();

        //Get by id
        public T getById(I i);

    }


    public interface Test2 extends Test<List<String>, String> {
        public String getByDate();
        public String getByDay();

    }


    public class DAOJDBC implements Test2 {

        @Override
        public void insert(List<String> strings) {

        }

        @Override
        public void delete(List<String> strings) {

        }

        @Override
        public void update(List<String> strings) {

        }

        @Override
        public List<List<String>> getAll() {
            return null;
        }

        @Override
        public List<String> getById(String s) {
            return null;
        }


        @Override
        public String getByDate() {
            return null;
        }

        @Override
        public String getByDay() {
            return null;
        }
    }


    public class DAOMongo implements Test2 {

        @Override
        public void insert(List<String> strings) {

        }

        @Override
        public void delete(List<String> strings) {

        }

        @Override
        public void update(List<String> strings) {

        }

        @Override
        public List<List<String>> getAll() {
            return null;
        }

        @Override
        public List<String> getById(String s) {
            return null;
        }


        @Override
        public String getByDate() {
            return null;
        }

        @Override
        public String getByDay() {
            return null;
        }

        public String getAllExercicis(){
            return null;
        }

    }

    public static void main(String[] args) {
        Test<String,Integer> test;
    }
}
