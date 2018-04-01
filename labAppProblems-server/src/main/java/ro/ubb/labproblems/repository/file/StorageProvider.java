package ro.ubb.labproblems.repository.file;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StorageProvider {

    private Storage storage;


    public StorageProvider() {
        storage = new Storage();
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Storage getStorage() {
        return storage;
    }


    @SuppressWarnings("unchecked")
    public <T extends BaseEntity<String>> List<T> get(Class<T> type) {
        switch (type.getSimpleName()) {
            case "Student":
                return (List<T>) storage.students;
            case "Problem":
                return (List<T>) storage.problems;
            case "Assignment":
                return (List<T>) storage.assignments;
        }
        throw new RuntimeException("Invalid storage requested");
    }

}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Storage {
    List<Student> students;

    List<Problem> problems;

    List<Assignment> assignments;

    Storage() {
        students = new ArrayList<>();
        problems = new ArrayList<>();
        assignments = new ArrayList<>();
    }
}
