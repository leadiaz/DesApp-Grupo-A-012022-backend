// deprecated code

//package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.util;
//
//import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoAsset;
//import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoAssetsEnum;
//import com.google.gson.Gson;
//import org.springframework.web.reactive.function.client.WebClient;
//
//public class  CryptoAssetValue {
//
//    private String urlAPI = "https://api1.binance.com/api/v3/ticker";
//
//    private WebClient webClient =  WebClient.create(urlAPI);
//
//    public CryptoAssetValue(WebClient webClient) {
//        this.webClient = webClient;
//    }
//
//    public float getCryptoAssetValue(CryptoAssetsEnum crypto ) {
//        CryptoAsset cryptoAsset = new CryptoAsset();
//        Gson g = new Gson();
//
//        String jsonString = webClient.get().uri("/price?symbol={cryptoName}",crypto.name())
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//
//        cryptoAsset = g.fromJson(jsonString, CryptoAsset.class);
//        return cryptoAsset.getValue();
//    }
//}
