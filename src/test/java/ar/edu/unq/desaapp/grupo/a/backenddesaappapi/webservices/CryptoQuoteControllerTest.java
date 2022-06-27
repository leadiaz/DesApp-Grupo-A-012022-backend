package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoQuote;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.CryptoQuoteService;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices.secure.CryptoQuoteController;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

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
    @Test
    public void testGetAllCryptoQuotes() throws Exception{
        CryptoQuote ALICEUSDT = new CryptoQuote("ALICEUSDT", "2.85000000", "360.2685");
        CryptoQuote MATICUSDT = new CryptoQuote("MATICUSDT", "0.62600000", "79.13266");
        CryptoQuote AXSUSDT = new CryptoQuote("AXSUSDT", "20.36000000", "2573.7078");
        CryptoQuote AAVEUSDT = new CryptoQuote("AAVEUSDT", "105.00000000", "13273.051");
        CryptoQuote ATOMUSDT = new CryptoQuote("ATOMUSDT", "9.38000000", "1185.7258");
        CryptoQuote NEOUSDT = new CryptoQuote("NEOUSDT", "11.97000000", "1513.1278");
        CryptoQuote DOTUSDT = new CryptoQuote("DOTUSDT", "9.42000000", "1190.7822");
        CryptoQuote ETHUSDT = new CryptoQuote("ETHUSDT", "1851.36000000", "234030.42");
        CryptoQuote CAKEUSDT = new CryptoQuote("CAKEUSDT", "4.53000000", "572.6373");
        CryptoQuote BTCUSDT = new CryptoQuote("BTCUSDT", "31254.67000000", "3950903.0");
        CryptoQuote BNBUSDT = new CryptoQuote("BNBUSDT", "293.20000000", "37063.414");
        CryptoQuote ADAUSDT = new CryptoQuote("ADAUSDT", "0.60340000", "76.275795");
        CryptoQuote TRXUSDT = new CryptoQuote("TRXUSDT", "0.08144000", "10.294831");
        CryptoQuote AUDIOUSDT = new CryptoQuote("AUDIOUSDT", "0.41300000", "52.20733");


        List<CryptoQuote> cryptos = Arrays.asList(ALICEUSDT, MATICUSDT, AXSUSDT, AAVEUSDT, ATOMUSDT, NEOUSDT, DOTUSDT, ETHUSDT, CAKEUSDT,BTCUSDT, BNBUSDT, ADAUSDT,TRXUSDT, AUDIOUSDT);
        when(service.getAllCryptoQuote()).thenReturn(cryptos);

        mvc.perform(get("/quote/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(14)));
    }

}
