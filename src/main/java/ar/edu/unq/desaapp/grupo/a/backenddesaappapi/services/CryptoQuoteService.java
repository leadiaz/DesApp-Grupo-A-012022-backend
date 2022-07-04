package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoQuote;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.BinanceResponce;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.CryptoQuoteDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.USDResponse;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories.CryptoQuoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CryptoQuoteService {
    private final CryptoQuoteRepository cryptoQuoteRepository;
    private List<String> cryptoSymbols = Arrays.asList(
            "ALICEUSDT",
            "MATICUSDT",
            "AXSUSDT",
            "AAVEUSDT",
            "ATOMUSDT",
            "NEOUSDT",
            "DOTUSDT",
            "ETHUSDT",
            "CAKEUSDT",
            "BTCUSDT",
            "BNBUSDT",
            "ADAUSDT",
            "TRXUSDT",
            "AUDIOUSDT"
    );
    private RestTemplate restTemplate = new RestTemplate();

    public CryptoQuoteService(CryptoQuoteRepository cryptoQuoteRepository) {
        this.cryptoQuoteRepository = cryptoQuoteRepository;
    }

    private CryptoQuote persistCryptoQuoteFromBinance(String cryptoType) {
        String url = "https://api1.binance.com/api/v3/ticker/price?symbol=" + cryptoType;
        BinanceResponce binanceResponce = restTemplate.getForObject(url, BinanceResponce.class);
        USDResponse usdResponse = getUSDQuoteToPesos();
        Float price = Float.valueOf(Objects.requireNonNull(binanceResponce).getPrice()) * Float.valueOf(usdResponse.getSale());
        return this.cryptoQuoteRepository.save(new CryptoQuote(cryptoType, binanceResponce.getPrice(), price.toString()));
    }
    public CryptoQuote getCryptoQuote(String cryptoType){
        List<CryptoQuote> cryptoQuotes = this.cryptoQuoteRepository.findByCrypto(cryptoType);
        if(!cryptoQuotes.isEmpty()){
            return cryptoQuotes.stream().sorted(Comparator.comparing(CryptoQuote::getDate).reversed()).collect(Collectors.toList()).get(0);
        }
        return this.persistCryptoQuoteFromBinance(cryptoType);
    }
    private USDResponse getUSDQuoteToPesos(){
        //API consumed from https://github.com/Castrogiovanni20/api-dolar-argentina
        String url = "https://api-dolar-argentina.herokuapp.com/api/dolaroficial";
        return restTemplate.getForObject(url, USDResponse.class);
    }

    public List<CryptoQuoteDto> getAllCryptoQuote() {
        return cryptoSymbols.stream().map(this::getCryptoQuote).map(CryptoQuote::toDto).collect(Collectors.toList());
    }
}
