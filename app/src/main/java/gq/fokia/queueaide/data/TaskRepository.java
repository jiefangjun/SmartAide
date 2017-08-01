package gq.fokia.queueaide.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by archie on 8/1/17.
 */

public class TaskRepository implements TaskDataSource {

    private static TaskRepository INSTANCE = null;

    private final TaskDataSource mTaskRemoteDataSource;

    Map<String, User> mCachedUsers;

    boolean mCacheIsDirty = false;

    private TaskRepository(TaskDataSource taskRemoteDataSource){
        mTaskRemoteDataSource = taskRemoteDataSource;
    }

    public static TaskRepository getInstance(TaskDataSource taskRemoteDataSource){
        if (INSTANCE == null){
            INSTANCE = new TaskRepository(taskRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    @Override
    public void getTasks(final LoadTasksCallback callback) {
        if (mCachedUsers != null && !mCacheIsDirty){
            callback.onTasksLoaded(new ArrayList<>(mCachedUsers.values()));
            return;
        }

        if (mCacheIsDirty){
            getTasksFromRemoteDataSource(callback);
        }else {
            mTaskRemoteDataSource.getTasks(new LoadTasksCallback() {
                @Override
                public void onTasksLoaded(List<User> users) {
                    refreshTasks();
                    callback.onTasksLoaded(new ArrayList<>(mCachedUsers.values()));
                }

                @Override
                public Void onDataNotAvailable() {
                    getTasksFromRemoteDataSource(callback);
                }
            });
        }

    }

    @Override
    public void getTask(String taskId, GetTaskCallback callback) {

    }

    @Override
    public void saveTask(User user) {
        mTaskRemoteDataSource.saveTask(user);

        if (mCachedUsers == null){
            mCachedUsers = new LinkedHashMap<>();
        }
        mCachedUsers.put(user.getId(), user);
    }

    @Override
    public void completeTask(User user) {
        mTaskRemoteDataSource.completeTask(user);

        User completedUser = new User(user.getId(), user.getWindowName(), user.getName(), user.getTime(), user.getFoodName(), true);
        if (mCachedUsers == null){
            mCachedUsers = new LinkedHashMap<>();
        }
        mCachedUsers.put(user.getId(), completedUser);
    }

    @Override
    public void completeTask(String taskId) {
        completeTask(getTaskWithId(taskId));

    }

    @Override
    public void activateTask(User user) {
        mTaskRemoteDataSource.activateTask(user);

        User activeTask = new User(user.getId(), user.getWindowName(), user.getName(), user.getTime(), user.getFoodName(), f)

    }

    @Override
    public void activateTask(String taskId) {
        activateTask(getTaskWithId(taskId));
    }

    @Override
    public void refreshTasks() {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(String taskId) {

    }
}
