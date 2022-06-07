package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoQuote;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.CryptoQuoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CryptoQuoteController.class)
public class CryptoQuoteControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CryptoQuoteService service;

    @Test
    public void testGetETHQuote() throws Exception{
        CryptoQuote crypto = new CryptoQuote("ETHUSDT", "1851.36000000", "234030.42");
        when(service.getCrytoQuote("ETHUSDT")).thenReturn(crypto);
        mvc.perform(get("/quote/{symbol}", "ETHUSDT").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usdPrice").value("1851.36000000"))
                .andExpect(jsonPath("$.pesosPrice").value("234030.42"));
    }

}
