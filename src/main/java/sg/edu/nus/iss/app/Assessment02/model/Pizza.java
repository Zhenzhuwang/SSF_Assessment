package sg.edu.nus.iss.app.Assessment02.model;

import java.io.Serializable;
import java.util.Random;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Pizza implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotNull(message = "The pizza selection should be one of the following: bella, margherita, marinara, spinanatacalabrese, or trioformaggio")
    private String pizza;
    @NotNull(message = "The Pizza size must be one of the following: sm, md, or lg")
    private String size;
    @Min(value = 1, message = "Minimum of order is 1!")
    @Max(value = 11, message="Maximum of order is 10!")
    private int quantity;

    @NotNull(message = "Customer's name, Mandatory field cannot be null")
    @Size(min=3, message = "Minimum 3 characters")
    private String name;
    @NotNull(message = "Address, Mandatory field cannot be null")
    private String address;
    @Size(min = 8, message = "Phone number must be at least 8 digit.")
    private String phonenumber;

    private boolean rush = false;
    private String comments;
    private String orderId;
    private double total;

    
    public String getPizza() {
        return pizza;
    }
    public void setPizza(String pizza) {
        this.pizza = pizza;
    }
    public @NotNull(message = "The Pizza size must be one of the following: sm, md, or lg") String getSize() {
        return size;
    }
    public void setSize(@NotNull(message = "The Pizza size must be one of the following: sm, md, or lg") String size) {
        this.size = size;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public @Size(min = 8, message = "Phone number must be at least 8 digit.") String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(@Size(min = 8, message = "Phone number must be at least 8 digit.") String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public boolean isRush() {
        return rush;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public void setOrderId(String orderId) {
    }
    public String getId() {
        return orderId;
    }
    public void setId(String id) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public Pizza() {
        this.orderId = this.generateId(8);
    }

    public Pizza(
            @NotNull(message = "The pizza selection should be one of the following: bella, margherita, marinara, spinanatacalabrese, or trioformaggio") String pizza,
            @NotNull(message = "The Pizza size must be one of the following: sm, md, or lg") String size,
            @Min(value = 1, message = "Minimum of order is 1!") @Max(value = 11, message = "Maximum of order is 10!") int quantity,
            @NotNull(message = "Customer's name, Mandatory field cannot be null") @Size(min = 3, message = "Minimum 3 characters") String name,
            @NotNull(message = "Address, Mandatory field cannot be null") String address,
            @Size(min = 8, message = "Phone number must be at least 8 digit.") String phonenumber, boolean rush,
            String comments, String orderId, double total) {
        this.pizza = pizza;
        this.size = size;
        this.quantity = quantity;
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
        this.rush = rush;
        this.comments = comments;
        this.orderId = this.generateId(8);
        this.total = total;
    }
    private synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < numChars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numChars);
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("pizza", this.getPizza())
                .add("size", this.getSize())
                .add("quantity", this.getQuantity())
                .add("name", this.getName())
                .add("phone", this.getPhonenumber())
                .add("address", this.getAddress())
                .add("rush", this.isRush())
                .add("comments", this.getComments())
                .add("total", this.getTotal())
                .add("orderId", this.getId())
                .build();
    }
    
}
