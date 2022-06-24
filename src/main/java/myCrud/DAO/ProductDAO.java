package myCrud.DAO;

import myCrud.Model.Customer;
import myCrud.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {

    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //show all products
    public List<Product> all(){
        return jdbcTemplate.query("SELECT * FROM product", new BeanPropertyRowMapper<>(Product.class));
    }

    //show product by id
    public Product showById(int id){
        return jdbcTemplate.query("SELECT * FROM product WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Product.class)).stream().findAny().orElse(null);
    }

    //save
    public void save(Product product){
        jdbcTemplate.update("INSERT INTO product (title, opis) VALUES (?,?)", product.getTitle(), product.getOpis());
    }

    //update
    public void update(Product updatedProduct, int id){
        jdbcTemplate.update("UPDATE product SET title =?, opis=? WHERE id =?", updatedProduct.getTitle(), updatedProduct.getOpis(), id);
    }

    //delete
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM product WHERE id=?", id);
    }
}
