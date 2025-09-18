import java.util.*;

public class TaskManager {

    private static class Task {
        int userId;
        int taskId;
        int priority;
        int version;

        Task(int userId, int taskId, int priority, int version) {
            this.userId = userId;
            this.taskId = taskId;
            this.priority = priority;
            this.version = version;
        }
    }

    private PriorityQueue<Task> maxHeap;
    private Map<Integer, Task> taskMap;      // taskId -> latest Task
    private int versionCounter = 0;

    public TaskManager(List<List<Integer>> tasks) {
        maxHeap = new PriorityQueue<>((a, b) -> {
            if (a.priority != b.priority) {
                return Integer.compare(b.priority, a.priority); // higher priority first
            }
            return Integer.compare(b.taskId, a.taskId); // higher taskId first
        });

        taskMap = new HashMap<>();

        for (List<Integer> t : tasks) {
            int userId = t.get(0), taskId = t.get(1), priority = t.get(2);
            Task task = new Task(userId, taskId, priority, versionCounter++);
            taskMap.put(taskId, task);
            maxHeap.offer(task);
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority, versionCounter++);
        taskMap.put(taskId, task);
        maxHeap.offer(task);
    }

    public void edit(int taskId, int newPriority) {
        Task oldTask = taskMap.get(taskId);
        if (oldTask == null) return;

        Task updatedTask = new Task(oldTask.userId, taskId, newPriority, versionCounter++);
        taskMap.put(taskId, updatedTask);
        maxHeap.offer(updatedTask);
    }

    public void rmv(int taskId) {
        taskMap.remove(taskId);
    }

    public int execTop() {
        while (!maxHeap.isEmpty()) {
            Task top = maxHeap.poll();
            Task current = taskMap.get(top.taskId);
            // Check if this is still the latest version
            if (current != null && current.version == top.version) {
                taskMap.remove(top.taskId);
                return top.userId;
            }
            // else: stale task, skip
        }
        return -1;
    }
}
