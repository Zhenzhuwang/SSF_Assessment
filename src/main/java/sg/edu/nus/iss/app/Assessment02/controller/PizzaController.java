package sg.edu.nus.iss.app.Assessment02.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import sg.edu.nus.iss.app.Assessment02.model.Pizza;
import sg.edu.nus.iss.app.Assessment02.service.PizzaService;

import ch.qos.logback.core.model.Model;

// @Controller
// @RequestMapping (path="/", consumes = "application/x-www-form-urlencoded;charset=UTF-8", produces = "application/x-www-form-urlencoded;charset=UTF-8")
// public class PizzaController {
    
//     @Autowired
//     private PizzaService pizzaService;
    
//     public String getPizza(@RequestParam(required = true) String selection, @RequestParam(required = true) int quantity, Model model){
//         return "index";
//     }

//     @PostMapping("/pizza")
//     public String savePizza(@Valid Pizza pizza, BindingResult result, 
//                 Model model, HttpServletResponse response){
//         if(result.hasErrors()){
//             return "view1";
//         }
//         pizzaService.save(pizza);
//         // model.addAttribute( "pizza", pizza);
//         response.setStatus(HttpServletResponse.SC_CREATED);
//         return "view1";
//     }

//     @PostMapping("/place/order")
//     public String placeOrder(Model model) {
//         return "view2";
//     }

    @Controller
public class PizzaController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/pizza")
    public String placeOrder(@Valid @ModelAttribute Pizza pizzaOrder, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Please correct the errors in the form.");
            return "view1";
        }
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        pizzaOrder.setOrderId(orderId);
        redisTemplate.opsForValue().set(orderId, pizzaOrder);
        redirectAttributes.addFlashAttribute("message", "Your order has been placed successfully. Order ID: " + orderId);
        return "view1";
    }


    @PostMapping("/place/order")
    public String placeOrder(Model model) {
        return "view2";
    }
}





// @Controller
// @RequestMapping(path="/")
// public class PizzaController {
//     @PostMapping("/pizza")
//     public String processOrder(@Valid @ModelAttribute Pizza pizzaOrder, BindingResult result, Model model) {
//         if (result.hasErrors()) {
//             return "View0";
//         }
//         // perform checks on the pizzaOrder
//         if (pizzaOrder.getSelection() == null) {
//             // model.addAttribute("errorMessage");
//             return "View0";
//         }
//         if (!pizzaOrder.getSelection().equals("Bella")
//                 && !pizzaOrder.getSelection().equals("Margherita")
//                 && !pizzaOrder.getSelection().equals("Marinara")
//                 && !pizzaOrder.getSelection().equals("Spianatacalabrese")
//                 && !pizzaOrder.getSelection().equals("Trioformaggio")) {
//             // model.addAttribute("errorMessage", "Invalid pizza selection.");
//             return "View0";
//         }
//         if (pizzaOrder.getSize() == null) {
//             // model.addAttribute("errorMessage", "Please select a pizza size.");
//             return "View0";
//         }
//         if (!pizzaOrder.getSize().equals("sm")
//                 && !pizzaOrder.getSize().equals("md")
//                 && !pizzaOrder.getSize().equals("lg")) {
//             // model.addAttribute("errorMessage", "Invalid pizza size.");
//             return "View0";
//         }
//         if (pizzaOrder.getQuantity() == 0 || pizzaOrder.getQuantity() < 1 || pizzaOrder.getQuantity() > 10) {
//             // model.addAttribute("errorMessage", "Invalid quantity. Please order between 1 and 10 pizzas.");
//             return "View0";
//         }
//         // process the order
//         return "view1";
//     }
// }
