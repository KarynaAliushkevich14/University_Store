package myCrud.Controllers;
import myCrud.DAO.SellerDAO;
import myCrud.Model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/seller")
public class SellerController {

    private SellerDAO sellerDAO;

    @Autowired
    public SellerController(SellerDAO sellerDao){
        this.sellerDAO=sellerDao;
    }

    //get list of sellers
    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("sellers", sellerDAO.all());
        return "seller/all";
    }

    //show by id
    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        model.addAttribute("seller", sellerDAO.showById(id));
        return "seller/showById";
    }

    //get html registration form
    @GetMapping("/new")
    public String newSeller(Model model) {
        model.addAttribute("seller", new Seller());
        return "seller/new";
    }

    //post
    @PostMapping()
    public String create(@ModelAttribute("seller") @Valid Seller seller,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "seller/new";

        sellerDAO.save(seller);
        return "redirect:/seller/all";
    }


    //edit html
    @GetMapping("/{id}/edit")
    public String editHtml(@PathVariable("id") int id, Model model) {
        model.addAttribute("seller", sellerDAO.showById(id));
        return "seller/edit";
    }

    //update
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("seller") @Valid Seller seller, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "seller/edit";
        }
        sellerDAO.update(seller, id);
        return "redirect:/seller/all";
    }

    //delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        sellerDAO.delete(id);
        return "redirect:/seller/all";
    }
    //если в таблице order появляется запись
    //то id_order заполняетя также как и выше
    //метод


    //metoda POST dla dodawania produktu


}

