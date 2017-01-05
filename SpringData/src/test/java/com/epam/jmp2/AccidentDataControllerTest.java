package com.epam.jmp2;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.jmp2.controller.AccidentDataController;
import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.model.RoadAccidentBuilder;
import com.epam.jmp2.service.AccidentService;

public class AccidentDataControllerTest {
	
	private MediaType contentType = new MediaType("application", "json", Charset.forName("UTF-8"));
	
	private MockMvc mockMvc;

    @Mock
    private AccidentService accidentService;

    @InjectMocks
    private AccidentDataController userController;
    
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    
    @Test
    public void shouldReturnListOfAccidentsWithFindAllRequestMapping() throws Exception {
    	
    	List<RoadAccident> roadAccidentList = getResponseRoadAccidentList();
        when(accidentService.findAll()).thenReturn(roadAccidentList);

        mockMvc.perform(get("/accidents"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].accidentId", is("1")))
                .andExpect(jsonPath("$[0].accidentSeverity", is("1")))
                .andExpect(jsonPath("$[0].roadSurfaceConditions", is("Bad road surface")))
                .andExpect(jsonPath("$[0].policeForce", is("Police Force")));

        verify(accidentService, times(1)).findAll();
        verifyNoMoreInteractions(accidentService);
    }
    
    
    @Test
    public void shouldReturnListOfAccidentsWithFindByRoadConditionsRequestMapping() throws Exception {
    	
    	List<RoadAccident> roadAccidentList = getResponseRoadAccidentList();
        when(accidentService.getAllAccidentsByRoadCondition(1)).thenReturn(roadAccidentList);

        mockMvc.perform(get("/findAccidentDetailsByRoadCondition/{roadCondition}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].accidentId", is("1")))
                .andExpect(jsonPath("$[0].accidentSeverity", is("1")))
                .andExpect(jsonPath("$[0].roadSurfaceConditions", is("Bad road surface")))
                .andExpect(jsonPath("$[0].policeForce", is("Police Force")));

        verify(accidentService, times(1)).getAllAccidentsByRoadCondition(1);
        verifyNoMoreInteractions(accidentService);
    }
    
    @Test
    public void shouldReturnListOfAccidentsWithFindByWeatherConditionsAndYearRequestMapping() throws Exception {
    	
        when(accidentService.getAllAccidentsByWeatherConditionAndYear(1, "2010")).thenReturn(new Long(1));

        mockMvc.perform(get("/findAccidentDetailsGroupByWeatherConditionAndYear/{weatherCondition}/{year}" , 1, 2010))
                .andExpect(status().isOk());

        verify(accidentService, times(1)).getAllAccidentsByWeatherConditionAndYear(1, "2010");
        verifyNoMoreInteractions(accidentService);
    }

	private List<RoadAccident> getResponseRoadAccidentList() {
		List<RoadAccident> roadaccidentByRoadSurfaceConditionList = new ArrayList<>(); 
		RoadAccidentBuilder roadAccident = new RoadAccidentBuilder();
		roadAccident.withAccidentId("1");
		roadAccident.withAccidentSeverity("1");
		roadAccident.withDistrictAuthority("");
		roadAccident.withLatitude(new Float(10.1));
		roadAccident.withLightConditions("Bad Light conditions");
		roadAccident.withLongitude(new Float(10.1));
		roadAccident.withNumberOfCasualties(1);
		roadAccident.withNumberOfVehicles(1);
		roadAccident.withPoliceForce("Police Force");
		roadAccident.withRoadSurfaceConditions("Bad road surface");
		roadAccident.withWeatherConditions("Bad weather");
		roadaccidentByRoadSurfaceConditionList.add(roadAccident.build());
		return roadaccidentByRoadSurfaceConditionList;
	}

}
