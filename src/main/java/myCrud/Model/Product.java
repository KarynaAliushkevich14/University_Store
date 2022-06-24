package myCrud.Model;

import javax.validation.constraints.NotEmpty;

public class Product {
    private int id;
    @NotEmpty(message = "Title should not be empty")
    private String title;
    @NotEmpty(message = "Description should not be empty")
    private String description;
    private int id_order;


    public Product(int id, String title, String description, int id_order) {
        this.id = id;
        this.title = title;
        this.description=description;
        this.id_order=id_order;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }
}
