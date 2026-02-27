package se.iths.yunus.javatools;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import se.iths.yunus.javatools.model.Food;
import se.iths.yunus.javatools.repository.FoodRepository;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ActiveProfiles("test")
class FoodIntegrationTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Autowired
    private FoodRepository foodRepository;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        foodRepository.deleteAll();
    }

    @Test
    void testCreateFood() throws Exception {
        mockMvc.perform(post("/foods/create")
                .param("name","Milk")
                .param("quantity","10")
                .param("price","20")
                .param("bestBefore","2026-05-05"))
                .andExpect(status().is3xxRedirection());
        assertEquals(1, foodRepository.count());
    }

    @Test
    void testFindFoodById() throws Exception {
        Food saved = foodRepository.save(
                new Food("Bread", true, false, "12345", 20, LocalDate.of(2026,12,12), 5)
        );
        mockMvc.perform(get("/foods/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Bread")));
    }

    @Test
    void testFindAllFoods() throws Exception {
        foodRepository.save(new Food ("Apple",false, false,"31245678",19,LocalDate.of(2026,4,6),20));
        foodRepository.save(new Food("Banana",false, false, "412345678",21,LocalDate.of(2026,4,8),40));
        foodRepository.save(new Food("Orange",false, false, "512345678", 25, LocalDate.of(2026,4,22),25));

        mockMvc.perform(get("/foods"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Apple")))
                .andExpect(content().string(containsString("Banana")))
                .andExpect(content().string(containsString("Orange")));
    }

    @Test
    void testShowFoodNotFound() throws Exception {
        mockMvc.perform(get("/foods/9999"))
                .andExpect(status().isOk())
                .andExpect(view().name("show-food"))
                .andExpect(content().string(containsString("not found")));
    }

    @Test
    void testShowEditFormNotFound() throws Exception{
        mockMvc.perform(get("/foods/edit/99999"))
                .andExpect(status().isOk())
                .andExpect(view().name("show-food"))
                .andExpect(content().string(containsString("not found")));

    }

    @Test
    void testUpdateFoodNotFound() throws Exception{
        mockMvc.perform(post("/foods/edit/9999")
                .param("name","Milk")
                .param("quantity","10")
                .param("price","20")
                .param("bestBefore","2026-05-05"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-food"))
                .andExpect(content().string(containsString("not found")));
    }
}
