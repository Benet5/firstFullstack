package todoApp;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoRepo extends MongoRepository<ToDoItem, String> {

 Optional<ToDoItem> findByName(String name);

  Optional<ToDoItem> deleteByName(String name);

  List<ToDoItem> findAllByFormattedEndDate (String deadline);
}
