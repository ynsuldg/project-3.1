package se.iths.yunus.javatools.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.yunus.javatools.exception.FoodAlreadyExistsException;
import se.iths.yunus.javatools.exception.FoodNotFoundException;
import se.iths.yunus.javatools.model.Food;

import se.iths.yunus.javatools.repository.FoodRepository;
import se.iths.yunus.javatools.validator.FoodValidator;

import java.time.LocalDate;
import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodValidator foodValidator;
    private static final Logger log = LoggerFactory.getLogger(FoodService.class);

    @Autowired

    public FoodService(FoodRepository foodRepository, FoodValidator foodValidator) {
        this.foodRepository = foodRepository;
        this.foodValidator = foodValidator;
    }



    // ================================= CREATE ==========================================
    public Food create(Food food) {
        foodValidator.validate(food);

       return foodRepository.save(food);
    }

    //================================== READ =============================================
    public List<Food>findAll(){
        log.info("Fetching all food items.");
        return foodRepository.findAll();
    }

    public Food findById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(()->{
                    log.warn("Food with id {} not found", id);
                    return new FoodNotFoundException(String.format("Food item with id %d is not found", id));
                });
    }

    public Food findByBarcode(String barcode) {
        return foodRepository.findByBarcode(barcode)
                .orElseThrow(()-> {
                    log.warn("Food with Barcode {} not found", barcode);
                    return new FoodNotFoundException(String.format("Food item with barcode %s is not found.", barcode));
                });
    }

    public Food findByName(String name) {
        return foodRepository.findByName(name)
                .orElseThrow(()-> {
                    log.warn("Food with name {} not found", name);
                    return new FoodNotFoundException(String.format("Food item with name %S is not found.", name));
                });
    }


    // Find some food that can make people allergic

    public List<Food>findByHasLactoseTrue(){
        return foodRepository.findByHasLactoseTrue();
    }

    public List<Food>findByHasSeafoodTrue(){
        return foodRepository.findByHasSeafoodTrue();
    }

    //Find if the food contains lactose
    public boolean findIfHasLactoseByBarcode(String barcode){
        Food food = this.findByBarcode(barcode);
        return food.getHasLactose();
    }
    public boolean findIfHasSeaFoodByBarcode(String barcode){
        Food food = this.findByBarcode(barcode);
        return food.getHasSeafood();
    }

    // Find food items with keywords
    public List<Food>findByNameContainingIgnoreCase(String name){
        return foodRepository.findByNameContainingIgnoreCase(name);
    }

    // About stocks.
    public List<Food>findOutOfStock(){
        return foodRepository.findByQuantityLessThanEqual(0);
    }

    public List<Food>findLowStock(int threshold) {
        return foodRepository.findByQuantityLessThanEqual(threshold);
    }

    //About expirations.
    public List<Food>findExpired(){
        return foodRepository.findByBestBeforeBefore(LocalDate.now());
    }

    public List<Food>findExpiringWithin(int threshold) {
        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(threshold);
        return foodRepository.findByBestBeforeBetween(today, limit);
    }


    // ================================= UPDATE ==========================================

    public Food update(Long id, Food updated){

        log.info("Updating food item with id {}.", id);

        foodValidator.validate(updated);

        Food existing = foodRepository.findById(id)
                .orElseThrow(()-> {
                    log.warn("Food with id {} not found during update", id);
                    return new FoodNotFoundException(String.format("Food with id %d not found", id));
                });

        setFoodAttributes(updated, existing);

        return foodRepository.save(existing);
    }

    public Food update(String barcode, Food updated) {
        log.info("Updating food item with barcode {}.", barcode);
        foodValidator.validate(updated);

        Food existing = foodRepository.findByBarcode(barcode)
                .orElseThrow(()-> {
                    log.warn("Food with Barcode {} not found during update", barcode);
                    return new FoodNotFoundException(String.format("Food item with barcode %s does not exist!", barcode));
                });
        setFoodAttributes(updated, existing);

        return foodRepository.save(existing);
    }


    // ==================================== DELETE ==========================================
    public void delete(Long id) {
        Food existing = foodRepository.findById(id)
                .orElseThrow(()-> {
                    log.warn("Attempt to delete food with id {} but it was not found",id);
                    return new FoodNotFoundException(String.format("Food with id %d not found", id));
                });
        log.info("Deleting food with id {}.", id);
        foodRepository.delete(existing);
    }

    public void delete( String barcode) {
        Food existing = foodRepository.findByBarcode(barcode)
                .orElseThrow(()->{
                    log.warn("Attempt to delete food item with barcode {} but it is not found.", barcode);
                    return new FoodNotFoundException(String.format("Food item with barcode %s is not found.", barcode));
                });

        foodRepository.delete(existing);
    }

    // ===================================== A private method only can be called within this class. =================
    private static void setFoodAttributes(Food updated, Food existing) {
        existing.setName(updated.getName());
        existing.setHasLactose(updated.getHasLactose());
        existing.setHasSeafood(updated.getHasSeafood());
        existing.setBarcode(updated.getBarcode());
        existing.setBestBefore(updated.getBestBefore());
        existing.setQuantity(updated.getQuantity());
        existing.setPrice(updated.getPrice());

    }

}
