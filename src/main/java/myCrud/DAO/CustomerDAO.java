package myCrud.DAO;

import myCrud.Model.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDAO {
    private static JdbcTemplate jdbcTemplate;
   // private List<Customer> allcustomers = new ArrayList<Customer>();

    public CustomerDAO (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //show all customers
    public List<Customer> all(){
        return jdbcTemplate.query("SELECT * FROM customer", new BeanPropertyRowMapper<>(Customer.class));
    }

    //show customer by id
    public Customer showById(int id){
        return jdbcTemplate.query("SELECT * FROM customer WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Customer.class)).stream().findAny().orElse(null);
    }

    //save
    public void save(Customer customer){
        jdbcTemplate.update("INSERT INTO customer (name, email, password) VALUES (?,?,?)", customer.getName(),customer.getEmail(), customer.getPassword());
    }

    //update
    public void update(Customer updatedcustomer, int id){
        jdbcTemplate.update("UPDATE customer SET name =?, email=?, password=? WHERE id =?", updatedcustomer.getName(), updatedcustomer.getEmail(), updatedcustomer.getPassword(), id);
    }

    //delete
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM customer WHERE id=?", id);
    }

}
