import java.util.HashMap;
import java.util.Map;

public class Service {

    private Map<Integer, Task> taskMap = new HashMap<>();  //   [1,tak11;2,task2;3,task3]

    public void add(Task task){
        taskMap.put(task.getId(), task);
    }

    public Task getTaskById(int number) {
//        int var1;
//        int var2;
//        System.out.println(var2);
//        String text1;

        return taskMap.get(number);
    }

    public int getMapSize() {
        return taskMap.size();
    }


    Task task;
    public Task delete(int id) {
        task = taskMap.get(id);
        task.setDeleted(true);

        return task;
    }

    public void printMap() {
        System.out.println(taskMap);
    }


}

