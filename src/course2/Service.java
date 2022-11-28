package course2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Service {
    private Map<Integer, Task> taskMap = new HashMap<>();
    private Collection<Task> removedTasks;

    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }

    public void remove(int id) {
        taskMap.remove(id);
    }

    Collection<Task> getAll(LocalDateTime localDate) {
        List<Task> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Task> integerTaskEntry : taskMap.entrySet()) {
            var task = integerTaskEntry.getValue();
            if (task.isAvailable(LocalDate.from(localDate))) {
                resultList.add(task);
            }
        }
        return resultList;
    }

}
