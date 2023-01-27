package sg.edu.nus.iss.app.Assessment02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import sg.edu.nus.iss.app.Assessment02.model.Pizza;

@Service
public class PizzaService {
    private static final String PIZZA_ENTITY = "pizzalist";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void save(final Pizza pizza) {
        redisTemplate.opsForList()
            .leftPush(PIZZA_ENTITY, pizza.getId());
        redisTemplate.opsForHash()
            .put( PIZZA_ENTITY+ "_Map", pizza.getId(), pizza);
    }
    
    public Pizza findById(final String pizzaId){
        Pizza result= (Pizza)redisTemplate.opsForHash()
                .get(PIZZA_ENTITY+ "_Map", 
                pizzaId);
        return result;
    }

    public List<Pizza> findAll(int startIndex){
        List<Object> fromPizzaList = redisTemplate.opsForList()
            .range(PIZZA_ENTITY, startIndex, 10);
        List<Pizza> pizzaService = redisTemplate.opsForHash()
            .multiGet(PIZZA_ENTITY+ "_Map", fromPizzaList)
            .stream()
            .filter(Pizza.class::isInstance)
            .map(Pizza.class::cast)
            .toList();
        
        return pizzaService;
    }

    public List<String> validateOrder(Pizza order) {
        return null;
    }

    public void submitOrder(Pizza order) {
    }
    
    public double calculateOrderCost(String pizzaType, String pizzaSize, int quantity, boolean rushOrder) {
        double basePrice = 0;
        double sizeMultiplier = 0;
        double totalCost = 0;
    
        // Determine the base price of the pizza based on the type selected
        switch (pizzaType) {
            case "Bella":
            case "Marinara":
            case "Souanata Calabrese":
                basePrice = 30;
                break;
            case "Magherita":
                basePrice = 22;
                break;
            case "Trio Formaggio":
                basePrice = 25;
                break;
        }
    
        // Determine the size multiplier based on the size selected
        switch (pizzaSize) {
            case "Small":
                sizeMultiplier = 1;
                break;
            case "Medium":
                sizeMultiplier = 1.2;
                break;
            case "Large":
                sizeMultiplier = 1.5;
                break;
        }
    
        // Calculate the total cost for that pizza
        totalCost = basePrice * sizeMultiplier * quantity;
    
        // Add $2 to the total cost if rush order option is selected
        if (rushOrder) {
            totalCost += 2;
        }
    
        return totalCost;
    }
    
}
