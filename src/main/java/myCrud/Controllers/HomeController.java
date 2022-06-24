package myCrud.Controllers;

import myCrud.DAO.CustomerDAO;
import myCrud.DAO.SellerDAO;
import myCrud.Model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    SellerDAO sellerdao;
    CustomerDAO customerdao;

    HomeController(SellerDAO sellerDao, CustomerDAO customerDao){
        this.sellerdao = sellerDao;
        this.customerdao = customerDao;
    }

    // html home page
    @GetMapping()
    public String homePage(Model model){
        model.addAttribute("person", new Person());
        return "home";
    }

    //post and wyciag danych z pól
    @PostMapping("OK")
    public String homePost(@ModelAttribute("person") Person person){
        //System.out.println(person.getEmail());
        // System.out.println(person.getPassword());
       String emailFromForm = person.getEmail();
       String passFromForm = person.getPassword();
       Person sellerPerson = sellerdao.check(person, emailFromForm);
       Person customerPerson = customerdao.check(person, emailFromForm);

       try{
      if(person.getEmail().equals(sellerPerson.getEmail()) ) {
          return "sellerAfterLog";
      }
       }catch (Exception e){
           System.out.println("SellerPerson is null");
       }

        try{
            if(person.getEmail().equals(customerPerson.getEmail()) ) {
                return "customerAfterLog";
            }
        }catch (Exception e){
            System.out.println("SellerPerson is null");
        }
        return "home";
    }

}
