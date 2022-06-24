package myCrud.Controllers;

import myCrud.DAO.ProductDAO;
import myCrud.Model.Product;
import myCrud.Model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

        private ProductDAO productDAO;

        @Autowired
        public ProductController(ProductDAO productDao){
            this.productDAO=productDao;
        }

        //get list of products
        @GetMapping("/all")
        public String all(Model model) {
            model.addAttribute("products", productDAO.all());
            return "product/all";
        }

        //show by id
        @GetMapping("/{id}")
        public String showById(@PathVariable("id") int id, Model model) {
            model.addAttribute("product", productDAO.showById(id));
            return "product/showById";
        }

        //get html registration form
        @GetMapping("/new")
        public String newProduct(Model model) {
            model.addAttribute("product", new Product());
            return "product/new";
        }

        //post
        @PostMapping()
        public String create(@ModelAttribute("product") @Valid Product product,
                             BindingResult bindingResult) {
            if (bindingResult.hasErrors())
                return "product/new";

            productDAO.save(product);
            return "redirect:/product/all";
        }


        //edit html
        @GetMapping("/{id}/edit")
        public String editHtml(@PathVariable("id") int id, Model model) {
            model.addAttribute("product", productDAO.showById(id));
            return "product/edit";
        }

        //update
        @PatchMapping("/{id}")
        public String update(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                             @PathVariable("id") int id) {
            if (bindingResult.hasErrors()) {
                return "product/edit";
            }
            productDAO.update(product, id);
            return "redirect:/product/all";
        }

        //delete
        @DeleteMapping("/{id}")
        public String delete(@PathVariable("id") int id) {
            productDAO.delete(id);
            return "redirect:/product/all";
        }
}
