package com.funbox.testapplication;

import com.funbox.testapplication.controllers.VisitorController;
import com.funbox.testapplication.model.DomainsDto;
import com.funbox.testapplication.model.LinksDto;
import com.funbox.testapplication.model.ResponseDto;
import com.funbox.testapplication.service.VisitorServiceBean;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(VisitorController.class)
public class RunApplicationTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private VisitorServiceBean visitorServiceBean;

    @Test
    public void create_visited_links_test() throws Exception {
        String body = "{ \"links\": [ link1.ru, link2.ru ] }";
        JSONObject jsonObject = new JSONObject(body);

        Mockito.when(visitorServiceBean.createVisitedLinks(Mockito.any(LinksDto.class), eq(System.currentTimeMillis())))
                .thenReturn(eq(Mockito.any(ResponseDto.class)));

        mvc.perform(MockMvcRequestBuilders.post("/api/visited_links")
                .content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void get_visited_domains_with_params_test() throws Exception {
        Mockito.when(visitorServiceBean.getVisitedDomains(eq(System.currentTimeMillis()), eq(System.currentTimeMillis())))
                .thenReturn(new DomainsDto());

        mvc.perform(MockMvcRequestBuilders.get("/api/visited_domains")
                .requestAttr("from", Mockito.anyLong())
                .requestAttr("to", Mockito.anyLong())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void get_visited_domains_test() throws Exception {
        Mockito.when(visitorServiceBean.getVisitedDomains(eq(System.currentTimeMillis()), eq(System.currentTimeMillis())))
                .thenReturn(new DomainsDto());

        mvc.perform(MockMvcRequestBuilders.get("/api/visited_domains")
                .requestAttr("from", Mockito.anyLong())
                .requestAttr("to", Mockito.anyLong())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
