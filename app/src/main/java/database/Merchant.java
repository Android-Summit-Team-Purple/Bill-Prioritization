package database;

/**
 * A merchant with an id, name, category.
 */
public class Merchant {

    public long id;
    public String name;
    public String category;

    public Merchant(long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Merchant(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
