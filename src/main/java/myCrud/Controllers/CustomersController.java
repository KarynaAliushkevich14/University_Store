package myCrud.Controllers;

import myCrud.DAO.CustomerDAO;
import myCrud.Model.Customer;
import myCrud.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomersController {

    private CustomerDAO customerDAO;

    @Autowired
    public CustomersController(CustomerDAO customerDao){
        this.customerDAO=customerDao;
    }

    //get list of customers
    @GetMapping("/all")
    public String all(Model model){
        model.addAttribute("customers", customerDAO.all());
        return "customer/all";
    }

    //show by id
    @GetMapping("/{id}")
    public String showById (@PathVariable ("id") int id, Model model){
        model.addAttribute("customer", customerDAO.showById(id));
        return "customer/showById";
    }

    //get html registration form
    @GetMapping("/new")
    public String newCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "customer/new";
    }

    //post
    @PostMapping()
    public String create(@ModelAttribute("customer") @Valid Customer customer,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "customer/new";

        customerDAO.save(customer);
        return "redirect:/customer/all";
    }


    //edit html
    @GetMapping("/{id}/edit")
    public String editHtml(@PathVariable ("id") int id, Model model){
        model.addAttribute("customer", customerDAO.showById(id));
        return "customer/edit";
    }

    //update
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult,
                         @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "customer/edit";
        }
        customerDAO.update(customer, id);
        return "redirect:/customer/all";
    }

    //delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        customerDAO.delete(id);
        return "redirect:/customer/all";
    }

    //если в таблице order появляется запись
    //то id_order заполняетя также как и выше
    //метод
}
