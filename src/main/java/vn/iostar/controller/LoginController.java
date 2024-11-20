package vn.iostar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vn.iostar.entity.Product;
import vn.iostar.services.ProductServices;

@Controller
public class LoginController {

    @Autowired
    private ProductServices service;

    @PostMapping("/login_success_handler")
    public String loginSuccessHandler() {
        System.out.println("Logging user login success...");
        return "index"; // Chuyển hướng đến trang index sau khi đăng nhập thành công
    }

    @PostMapping("/login_failure_handler")
    public String loginFailureHandler() {
        System.out.println("Login failure handler....");
        return "login"; // Chuyển hướng đến trang login nếu đăng nhập thất bại
    }

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "index"; // Trang index hiển thị danh sách sản phẩm
    }
    
    @RequestMapping("/new")
    public String showNewProductForm(Model model, @ModelAttribute("product") Product product) {
        model.addAttribute("product", product); // Add a new, empty Product object to the model
        return "new_product"; // Return the name of the view (JSP or Thymeleaf template)
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product); // Save the product using a service layer
        return "redirect:/"; // Redirect to the root URL after saving
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductForm(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("edit_product"); // Create a ModelAndView object
        Product product = service.get(id); // Retrieve the product by ID
        mav.addObject("product", product); // Add the product to the model
        return mav; // Return the ModelAndView object
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        service.delete(id); // Delete the product using a service layer
        return "redirect:/"; // Redirect to the root URL after deleting
    }
}
