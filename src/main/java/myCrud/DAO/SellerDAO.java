package myCrud.DAO;

import myCrud.Model.Customer;
import myCrud.Model.Person;
import myCrud.Model.Seller;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SellerDAO {

    private static JdbcTemplate jdbcTemplate;

    public SellerDAO (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //show all sellers
    public List<Seller> all(){
        return jdbcTemplate.query("SELECT * FROM seller", new BeanPropertyRowMapper<>(Seller.class));
    }

    //show seller by id
    public Seller showById(int id){
        return jdbcTemplate.query("SELECT * FROM seller WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Seller.class)).stream().findAny().orElse(null);
    }

    //save
    public void save(Seller seller){
        jdbcTemplate.update("INSERT INTO seller (name, email, password, NIP) VALUES (?,?,?,?)", seller.getName(), seller.getEmail(), seller.getPassword(), seller.getNip());
    }

    //update
    public void update(Seller updatedseller, int id){
        jdbcTemplate.update("UPDATE seller SET name =?, email=?, password=?, NIP=? WHERE id =?", updatedseller.getName(), updatedseller.getEmail(), updatedseller.getPassword(), updatedseller.getNip(), id);
    }

    //delete
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM seller WHERE id=?", id);
    }

    //check
    public Person check(Person seller, String email) {
        return jdbcTemplate.query("SELECT * FROM seller WHERE email=?", new Object[]{ email}, new BeanPropertyRowMapper<>(Seller.class)).stream().findAny().orElse(null);
    }
}
