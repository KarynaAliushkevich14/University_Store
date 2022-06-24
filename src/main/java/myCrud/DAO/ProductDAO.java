package myCrud.DAO;

import myCrud.Model.Product;
import myCrud.Model.Seller;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {

    private static JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //show all sellers
    public List<Product> all() {
        return jdbcTemplate.query("SELECT id, title, description FROM product", new BeanPropertyRowMapper<>(Product.class));
    }

    //show seller by id
    public Product showById(int id) {
        return jdbcTemplate.query("SELECT id,title,description FROM product WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Product.class)).stream().findAny().orElse(null);
    }

    //save
    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO product (title, description) VALUES (?,?)", product.getTitle(), product.getDescription());
    }

    //update
    public void update(Product updatedproduct, int id) {
        jdbcTemplate.update("UPDATE product SET title =?, description=? WHERE id =?", updatedproduct.getTitle(), updatedproduct.getDescription(), id);
    }

    //delete
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM product WHERE id=?", id);
    }
}