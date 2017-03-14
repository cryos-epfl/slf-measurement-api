package ch.epfl.cryos.osper.measurement.controller;

import ch.epfl.cryos.osper.measurement.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@TestPropertySource(locations = {"classpath:test.properties"})
@ActiveProfiles({"insecure", "test"})
public class TimeseriesControllerTest {

    private final String MEASUREMENT_PATH = "/measurement";
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void groups() throws Exception {
        this.mockMvc.perform(get(MEASUREMENT_PATH + "/groups"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void timeseriesOfStation() throws Exception {
        this.mockMvc.perform(get(MEASUREMENT_PATH + "/station/118509/timeseries"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(15)));
    }

    @Test
    public void timeseriesOfNonExistingStation() throws Exception {
        this.mockMvc.perform(get(MEASUREMENT_PATH + "/station/1/timeseries"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void timeserieWithInfoAndData() throws Exception {
        this.mockMvc.perform(get(MEASUREMENT_PATH + "/timeseries/63?includeData=true&includeInfo=true&from=2016-11-17T13:00Z&until=2016-11-17T14:00Z"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.info.id", equalTo(63)))
                .andExpect(jsonPath("$.info.stationId", equalTo(657723)))
                .andExpect(jsonPath("$.measurements").isArray())
                .andExpect(jsonPath("$.measurements", hasSize(3)));
    }

    @Test
    public void timeserieWithInfo() throws Exception {
        this.mockMvc.perform(get(MEASUREMENT_PATH + "/timeseries/63?includeData=false&includeInfo=true&from=2016-11-17T13:00Z&until=2016-11-17T14:00Z"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.info.id", equalTo(63)))
                .andExpect(jsonPath("$.info.stationId", equalTo(657723)))
                .andExpect(jsonPath("$.measurements").doesNotExist());
    }

    @Test
    public void timeserieWithData() throws Exception {
        this.mockMvc.perform(get(MEASUREMENT_PATH + "/timeseries/63?includeData=true&includeInfo=false&from=2016-11-17T13:00Z&until=2016-11-17T14:00Z"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.info").doesNotExist())
                .andExpect(jsonPath("$.measurements").isArray())
                .andExpect(jsonPath("$.measurements", hasSize(3)));
    }

    @Test
    public void timeserieWithInfoAndDataOnlyDate() throws Exception {
        this.mockMvc.perform(get(MEASUREMENT_PATH + "/timeseries/63?includeData=true&includeInfo=true&from=2016-11-17&until=2016-11-18"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.info.id", equalTo(63)))
                .andExpect(jsonPath("$.info.stationId", equalTo(657723)))
                .andExpect(jsonPath("$.measurements").isArray())
                .andExpect(jsonPath("$.measurements", hasSize(49)));
    }

    @Test
    public void timeserieNotFound() throws Exception {
        this.mockMvc.perform(get(MEASUREMENT_PATH + "/timeseries/999999"))
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error",equalTo("Not Found")));
    }

    @Test
    public void stationBadRequest() throws Exception {
        this.mockMvc.perform(get(MEASUREMENT_PATH + "/timeseries/yyy"))
//                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error",equalTo("Bad Request")));
    }





}
