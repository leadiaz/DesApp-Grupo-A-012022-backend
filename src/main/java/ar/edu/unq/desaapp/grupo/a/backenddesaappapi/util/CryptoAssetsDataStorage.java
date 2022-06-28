// deprecated code

//package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.util;
//
//import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoAssetsEnum;
//import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.HourlyCryptoAssetData;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.time.format.DateTimeFormatter;
//
//
//public class CryptoAssetsDataStorage {
//
//    private CryptoAssetValue cryptoAssetValue;
//
//    @Scheduled(fixedRate=60*60*1000)
//    public void hourlyStoreAllCrytoAssetsPrices(){
//        storeAllCrytoAssetsPrices();
//    }
//
//    public void storeAllCrytoAssetsPrices(){
//        for (CryptoAssetsEnum ca : CryptoAssetsEnum.values()) {
//            HourlyCryptoAssetData hourlyCryptoAssetData =
//                    new HourlyCryptoAssetData(ca,
//                            java.time.LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE),
//                            java.time.LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE),
//                            cryptoAssetValue.getCryptoAssetValue(ca));
//
//            //todo: persistance of hourlyCryptoAssetData
//        }
//
//    }
//
//}
