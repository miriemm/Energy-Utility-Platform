package ro.personal.energy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ro.personal.energy.EnergyUtilityTestConfig;
import ro.personal.energy.services.PersonService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class PersonControllerUnitTest extends EnergyUtilityTestConfig {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;


//    @Test
//    public void insertPersonTest() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        PersonDetailsDTO personDTO = new PersonDetailsDTO("John", "Somewhere Else street", 22);
//
//        mockMvc.perform(post("/person")
//                .content(objectMapper.writeValueAsString(personDTO))
//                .contentType("application/json"))
//                .andExpect(status().isCreated());
//    }

//    @Test
//    public void insertPersonTestFailsDueToAge() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        PersonDetailsDTO personDTO = new PersonDetailsDTO("John", "Somewhere Else street", 17);
//
//        mockMvc.perform(post("/person")
//                .content(objectMapper.writeValueAsString(personDTO))
//                .contentType("application/json"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void insertPersonTestFailsDueToNull() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        PersonDetailsDTO personDTO = new PersonDetailsDTO("John", null, 17);
//
//        mockMvc.perform(post("/person")
//                .content(objectMapper.writeValueAsString(personDTO))
//                .contentType("application/json"))
//                .andExpect(status().isBadRequest());
//    }
}
