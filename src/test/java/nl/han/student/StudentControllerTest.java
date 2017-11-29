package nl.han.student;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StudentControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                 .apply(SecurityMockMvcConfigurers.springSecurity())
                                 .build();
	}

	@Test
    @WithMockUser
	public void getRoot() throws Exception {
		mockMvc.perform(get("/"))
			   .andExpect(model().attributeExists("name"))
               .andExpect(model().attribute("name", "Students"))
			   .andExpect(model().attributeExists("studentForm"))
			   .andExpect(model().attributeExists("students"))
               .andExpect(model().attribute("students", Matchers.hasSize(2)))
			   .andExpect(view().name("home"));
	}

    @Test
    @WithMockUser
    public void testEmptyFormIsNotAllowed() throws Exception {
        mockMvc.perform(post("/add"))
               .andExpect(status().isOk())
               .andExpect(model().attributeHasErrors("studentForm"))
               .andExpect(model().attributeHasFieldErrors("studentForm", "nummer"))
               .andExpect(model().attributeHasFieldErrorCode("studentForm", "nummer", "NotNull"))
               .andExpect(model().attributeHasFieldErrors("studentForm", "naam"))
               .andExpect(model().attributeHasFieldErrorCode("studentForm", "naam", "NotNull"))
               .andExpect(view().name("home"));
    }

    @Test
    @WithMockUser
    public void testInvalidNummerSizeNotAllowed() throws Exception {
        mockMvc.perform(
                post("/add")
                        .param("nummer", "105")
                        .param("naam", "Pietje"))
               .andExpect(status().isOk())
               .andExpect(model().attributeHasErrors("studentForm"))
               .andExpect(model().attributeHasFieldErrors("studentForm", "nummer"))
               .andExpect(model().attributeHasFieldErrorCode("studentForm", "nummer", "Pattern"))
               .andExpect(view().name("home"));
    }

    @Test
    @WithMockUser
    public void testPostSuccess() throws Exception {
	    Student student = new Student(105195, "Pietje");

        mockMvc.perform(
                post("/add")
                        .param("nummer", String.valueOf(student.getNummer()))
                        .param("naam", student.getNaam()))
               .andExpect(model().hasNoErrors())
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/"));

        mockMvc.perform(
                get("/"))
               .andExpect(view().name("home"))
               .andExpect(model().attribute("students", Matchers.hasSize(3)));

    }
}
