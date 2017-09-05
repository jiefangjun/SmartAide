package gq.fokia.smartaide.data;

import java.util.List;

import gq.fokia.smartaide.model.User;

/**
 * Created by archie on 8/1/17.
 */

public interface TaskDataSource {

    interface LoadTasksCallback{

        void onTasksLoaded(List<User> users);

        void onDataNotAvailable();
    }

    interface GetTaskCallback{

        void onTaskLoaded(User user);

        void onDataNotAvailable();
    }

    void getTasks(LoadTasksCallback callback);

    void getTask(String taskId, GetTaskCallback callback);

    void saveTask(User user);

    void completeTask(User user);

    void completeTask(String taskId);

    void activateTask(User user);

    void activateTask(String taskId);

    void refreshTasks();

    void deleteAllTasks();

    void deleteTask(String taskId);
}
