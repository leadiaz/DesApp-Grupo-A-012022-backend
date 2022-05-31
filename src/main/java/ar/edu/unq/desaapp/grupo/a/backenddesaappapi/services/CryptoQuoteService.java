package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoQuote;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.BinanceResponce;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.USDResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class CryptoQuoteService {
    private RestTemplate restTemplate = new RestTemplate();
    public CryptoQuote getCrytoQuote(String cryptoType) {
        String url = "https://api1.binance.com/api/v3/ticker/price?symbol=" + cryptoType;
        BinanceResponce binanceResponce = restTemplate.getForObject(url, BinanceResponce.class);
        USDResponse usdResponse = getUSDQuoteToPesos();
        Float price = Float.valueOf(Objects.requireNonNull(binanceResponce).getPrice()) * Float.valueOf(usdResponse.getSale());
        return new CryptoQuote(cryptoType, binanceResponce.getPrice(), price.toString());
    }
    private USDResponse getUSDQuoteToPesos(){
        //API consumed from https://github.com/Castrogiovanni20/api-dolar-argentina
        String url = "https://api-dolar-argentina.herokuapp.com/api/dolaroficial";
        return restTemplate.getForObject(url, USDResponse.class);
    }
}
