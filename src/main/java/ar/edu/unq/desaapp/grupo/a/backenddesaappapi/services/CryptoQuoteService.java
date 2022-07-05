package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoQuote;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.BinanceResponce;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.CryptoQuoteDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.USDResponse;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories.CryptoQuoteRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CryptoQuoteService {
    private final CryptoQuoteRepository cryptoQuoteRepository;
    private final RedisTemplate redisTemplate;
    private static String HASH_KEY = "CryptoQuote";
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

    public CryptoQuoteService(CryptoQuoteRepository cryptoQuoteRepository, RedisTemplate redisTemplate) {
        this.cryptoQuoteRepository = cryptoQuoteRepository;
        this.redisTemplate = redisTemplate;
    }

    private CryptoQuote persistCryptoQuoteFromBinance(String cryptoType) {
        String url = "https://api1.binance.com/api/v3/ticker/price?symbol=" + cryptoType;
        BinanceResponce binanceResponce = restTemplate.getForObject(url, BinanceResponce.class);
        USDResponse usdResponse = getUSDQuoteToPesos();
        Float price = Float.valueOf(Objects.requireNonNull(binanceResponce).getPrice()) * Float.valueOf(usdResponse.getSale());
        return this.save(new CryptoQuote(cryptoType, binanceResponce.getPrice(), price.toString()));
    }
    private CryptoQuote save(CryptoQuote cryptoQuote){
        List<CryptoQuote> list = this.findByCrypto(cryptoQuote.getCrypto());
        if(list.size()>144){
            list.remove(0);
        }
        list.add(cryptoQuote);
        redisTemplate.opsForHash().put(HASH_KEY, cryptoQuote.getCrypto(), list);
        return cryptoQuote;
    }
    private List<CryptoQuote> findByCrypto(String cryptoType){
        List<CryptoQuote> cryptoQuotes = (List<CryptoQuote>) this.redisTemplate.opsForHash().get(HASH_KEY, cryptoType);
        return cryptoQuotes != null ? cryptoQuotes : new ArrayList<>();
    }
    public CryptoQuote getCryptoQuote(String cryptoType){
        List<CryptoQuote> cryptoQuotes = findByCrypto(cryptoType);
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
    @Scheduled(fixedRate = 600000)//every 10 minutes
    public void getNewQuotes(){
        for (String cryptoSymbol : cryptoSymbols) {
            persistCryptoQuoteFromBinance(cryptoSymbol);
        }
    }

    public List<CryptoQuoteDto> getLatestQuote(String cryptoQuote) {
        return this.findByCrypto(cryptoQuote)
                .stream()
                .sorted(Comparator.comparing(CryptoQuote::getDate).reversed())
                .map(CryptoQuote::toDto)
                .collect(Collectors.toList());
    }
}
