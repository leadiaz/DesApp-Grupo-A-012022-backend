package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.util;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoAsset;
import com.google.gson.Gson;
import org.springframework.web.reactive.function.client.WebClient;

public class  CryptoAssetValue {

    enum CryptoAssets {
        ALICEUSDT,
        MATICUSDT,
        AXSUSDT,
        AAVEUSDT,
        ATOMUSDT,
        NEOUSDT,
        DOTUSDT,
        ETHUSDT,
        CAKEUSDT,
        BTCUSDT,
        BNBUSDT,
        ADAUSDT,
        TRXUSDT,
        AUDIOUSDT
    }

    private String urlAPI = "https://api1.binance.com/api/v3/ticker";

    private WebClient webClient =  WebClient.create(urlAPI);

    public CryptoAssetValue(WebClient webClient) {
        this.webClient = webClient;
    }

    public float getCryptoAssetValue(CryptoAssets crypto ) {
        CryptoAsset cryptoAsset = new CryptoAsset();
        Gson g = new Gson();

        String jsonString = webClient.get().uri("/price?symbol={cryptoName}",crypto.name())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        cryptoAsset = g.fromJson(jsonString, CryptoAsset.class);
        return cryptoAsset.getValue();
    }
}
