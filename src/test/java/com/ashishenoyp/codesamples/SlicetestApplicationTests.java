// --------------------------------------------
// AUTHOR: Ashish Shenoy (ashishenoyp@gmail.com)
// --------------------------------------------

package com.ashishenoyp.codesamples;

import com.ashishenoyp.codesamples.api.ApiController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SlicetestApplication.class)
@WebAppConfiguration
public class SlicetestApplicationTests {
    private MockMvc mockMvc;

    @Autowired
    private ApiController apiController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }

    @Test
    public void testWordCountNullWord() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/service?"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testWordCountEmptyWord() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/service?word="))
                .andExpect(status().isBadRequest());
    }

    @Test
	public void testWordCountValidWord() throws Exception {
        // Perform the first request:
        mockMvc.perform(MockMvcRequestBuilders.get("/service?word=little"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"queryWord\":\"little\",\"numWordRequests\":1,\"numWordOccurrences\":4}"));

        // Perform a second request:
        mockMvc.perform(MockMvcRequestBuilders.get("/service?word=little"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"queryWord\":\"little\",\"numWordRequests\":2,\"numWordOccurrences\":4}"));

        // Perform a third request for the same word but in upper case:
        mockMvc.perform(MockMvcRequestBuilders.get("/service?word=LITTLE"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"queryWord\":\"little\",\"numWordRequests\":3,\"numWordOccurrences\":4}"));
    }


    @Test
    public void testWordCountInvalidWord() throws Exception {
        // Perform the first request:
        mockMvc.perform(MockMvcRequestBuilders.get("/service?word=slice"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"queryWord\":\"slice\",\"numWordRequests\":1,\"numWordOccurrences\":0}"));

        // Perform a second request:
        mockMvc.perform(MockMvcRequestBuilders.get("/service?word=slice"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"queryWord\":\"slice\",\"numWordRequests\":2,\"numWordOccurrences\":0}"));

        // Perform a third request for the same word but in upper case:
        mockMvc.perform(MockMvcRequestBuilders.get("/service?word=SLiCE"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"queryWord\":\"slice\",\"numWordRequests\":3,\"numWordOccurrences\":0}"));
    }
}
