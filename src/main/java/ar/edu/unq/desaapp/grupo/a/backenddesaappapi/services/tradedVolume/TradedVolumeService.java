// deprecated code

//package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services.tradedVolume;
//
//import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoAsset;
//import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;
//
//import java.time.LocalDate;
//
//public class TradedVolumeService {
//    public static TradedVolume getTradedVolume(LocalDate from, LocalDate to, CryptoAsset cryptoAsset, User userAsking){
//        //empaquetado en un objeto para despues de ahi sacar la data
//        Assets asset = new Assets(cryptoAsset, getTotalNominalAmountCryptoAsset(cryptoAsset), getCurrentValueCryptoAsset(cryptoAsset),getCurrentValueCryptoAssetInArgentinianPesos(cryptoAsset));
//
//        TradedVolume tradedVolume = new TradedVolume(userAsking,
//                LocalDate.now(),
//                getTotalTradedUSD(),
//                getTotalTradedArgentinianPesos(),
//                asset);
//
//        return tradedVolume;
//    }
//
//    private static float getTotalTradedUSD(){
//
//        return 0;
//    }
//
//    private static float getTotalTradedArgentinianPesos(){
//
//        return 0;
//    }
//
//    private static float getTotalNominalAmountCryptoAsset(CryptoAsset cryptoAsset){
//        return 0;
//    }
//
//    private static float getCurrentValueCryptoAsset(CryptoAsset cryptoAsset){
//        return 0;
//    }
//
//    private static float getCurrentValueCryptoAssetInArgentinianPesos(CryptoAsset cryptoAsset){
//        return 0;
//    }
//}
