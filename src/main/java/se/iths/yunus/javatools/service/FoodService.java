package se.iths.yunus.javatools.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.yunus.javatools.exception.FoodAlreadyExistsException;
import se.iths.yunus.javatools.exception.FoodNotFoundException;
import se.iths.yunus.javatools.model.Food;
import se.iths.yunus.javatools.respository.FoodRepository;
import se.iths.yunus.javatools.validator.FoodValidator;

import java.time.LocalDate;
import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodValidator foodValidator;

    @Autowired

    public FoodService(FoodRepository foodRepository, FoodValidator foodValidator) {
        this.foodRepository = foodRepository;
        this.foodValidator = foodValidator;
    }



    // ================================= CREATE ==========================================
    public Food create(Food food) {
        foodValidator.validate(food);

       if(foodRepository.findById(food.getId()).isPresent()){
           throw new FoodAlreadyExistsException(String.format("A food item with ID: %d already exists", food.getId()));
       }
       return foodRepository.save(food);
    }

    //================================== READ =============================================
    public List<Food>findAll(){
        return foodRepository.findAll();
    }

    public Food findById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(()->new FoodNotFoundException(String.format("Food item with id %d is not found", id)));
    }

    public Food findByBarcode(String barcode) {
        return foodRepository.findByBarcode(barcode)
                .orElseThrow(()-> new FoodNotFoundException(String.format("Food item with barcode %s is not found.", barcode)));
    }

    public Food findByName(String name) {
        return foodRepository.findByName(name)
                .orElseThrow(()->new FoodNotFoundException(String.format("Food item with name %S is not found.", name)));
    }


    // Find some food that can make people allergic

    public List<Food>findByHasLactoseTrue(){
        return foodRepository.findAllHasLactose();
    }

    public List<Food>findByHasSeafoodTrue(){
        return foodRepository.findAllHasSeafood();
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

        foodValidator.validate(updated);

        Food existing = foodRepository.findById(id)
                .orElseThrow(()-> new FoodNotFoundException(String.format("Food with id %d not found", id)));

        setFoodAttributes(updated, existing);

        return foodRepository.save(existing);
    }

    public Food update(String barcode, Food updated) {
        foodValidator.validate(updated);

        Food existing = foodRepository.findByBarcode(barcode)
                .orElseThrow(()-> new FoodNotFoundException(String.format("Food item with barcode %s does not exist!", barcode)));
        setFoodAttributes(updated, existing);

        return foodRepository.save(existing);
    }


    // ==================================== DELETE ==========================================
    public void delete(Long id) {
        Food existing = foodRepository.findById(id)
                .orElseThrow(()-> new FoodNotFoundException(String.format("Food with id %d not found", id)));

        foodRepository.delete(existing);
    }

    public void delete( String barcode) {
        Food existing = foodRepository.findByBarcode(barcode)
                .orElseThrow(()->new FoodNotFoundException(String.format("Food item with barcode %s is not found.", barcode)));

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
