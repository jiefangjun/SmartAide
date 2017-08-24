package gq.fokia.smartaide.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

/**
 * Created by archie on 7/12/17.
 */
public class User {

    private String id;
    private String name;
    private String time;
    private String windowName;
    private String foodName;
    private boolean mCompleted;

    public User(){

    }

    public User(String id, String name, String time, String windowName, String foodName){
        this(id, name, time, windowName, foodName, false);
    }

    public User(String name, String time, String windowName, String foodName){
        this(UUID.randomUUID().toString(), name, time, windowName, foodName, false);
    }

    public User(String windowName, String name, String time, String foodName, boolean completed){
        this(UUID.randomUUID().toString(), windowName, name, time, foodName, completed);
    }

    public User(String id, String windowName, String name, String time, String foodName, boolean completed) {
        this.id = id;
        this.windowName = windowName;
        this.name = name;
        this.time = time;
        this.foodName = foodName;
        this.mCompleted = completed;
    }



    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }
}
