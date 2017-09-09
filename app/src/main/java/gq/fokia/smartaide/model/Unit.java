package gq.fokia.smartaide.model;

public class Unit {

    private int id;
    private String userAvatar;
    private String restaurantName;
    private String userName;
    private String productName;
    private String time;

    public Unit(int id, String userAvatar, String restaurantName, String userName, String productName, String time) {
        this.id = id;
        this.userAvatar = userAvatar;
        this.restaurantName = restaurantName;
        this.userName = userName;
        this.productName = productName;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
