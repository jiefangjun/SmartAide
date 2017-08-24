package gq.fokia.smartaide.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
                public void onDataNotAvailable() {
                    getTasksFromRemoteDataSource(callback);
                }
            });
        }

    }

    @Override
    public void getTask(final String taskId, final GetTaskCallback callback) {

        final User cachedUser = getTaskWithId(taskId);

        if(cachedUser != null){
            callback.onTaskLoaded(cachedUser);
            return;
        }

        mTaskRemoteDataSource.getTask(taskId, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(User user) {

                if (mCachedUsers == null){
                    mCachedUsers = new LinkedHashMap<>();
                }
                mCachedUsers.put(user.getId(), user);
                callback.onTaskLoaded(user);
            }

            @Override
            public void onDataNotAvailable() {
                mTaskRemoteDataSource.getTask(taskId, new GetTaskCallback() {
                    @Override
                    public void onTaskLoaded(User user) {

                        if (mCachedUsers == null){
                            mCachedUsers = new LinkedHashMap<String, User>();
                        }
                        mCachedUsers.put(user.getId(), user);
                        callback.onTaskLoaded(user);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                });

            }
        });

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

        User activeTask = new User(user.getId(), user.getWindowName(), user.getName(), user.getTime(), user.getFoodName(), false);

        if (mCachedUsers == null){
            mCachedUsers = new LinkedHashMap<>();
        }
        mCachedUsers.put(user.getId(), activeTask);

    }

    @Override
    public void activateTask(String taskId) {
        activateTask(getTaskWithId(taskId));
    }

    @Override
    public void refreshTasks() {
        mCacheIsDirty = true;
    }

    @Override
    public void deleteAllTasks() {
        mTaskRemoteDataSource.deleteAllTasks();

        if (mCachedUsers == null){
            mCachedUsers = new LinkedHashMap<>();
        }
        mCachedUsers.clear();
    }

    @Override
    public void deleteTask(String taskId) {
        mTaskRemoteDataSource.deleteTask(taskId);
        mCachedUsers.remove(taskId);
    }

    private void getTasksFromRemoteDataSource(final LoadTasksCallback callback){
        mTaskRemoteDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<User> users) {
                refreshCache(users);
                refreshLocalDataSource(new ArrayList<User>(mCachedUsers.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<User> users) {
        if (mCachedUsers == null) {
            mCachedUsers = new LinkedHashMap<>();
        }
        mCachedUsers.clear();
        for (User user : users) {
            mCachedUsers.put(user.getId(), user);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<User> users) {
        //mTasksLocalDataSource.deleteAllTasks();
        for (User user : users) {
            //mTasksLocalDataSource.saveTask(task);
        }
    }

    @Nullable
    private User getTaskWithId(@NonNull String id) {
        if (mCachedUsers == null || mCachedUsers.isEmpty()) {
            return null;
        } else {
            return mCachedUsers.get(id);
        }
    }
}
