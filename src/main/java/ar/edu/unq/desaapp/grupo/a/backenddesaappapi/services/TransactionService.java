package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoAssetsEnum;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.CryptoQuote;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Intention;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Transaction;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.IntentionDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.TradedVolumeDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.TransactionDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.request.TransactionRequest;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.UserInfoOperation;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final IntentionService intentionService;
    private final UserInfoOperationService userInfoOperationService;
    private final CryptoQuoteService cryptoQuoteService;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserService userService, IntentionService intentionService, UserInfoOperationService userInfoOperationService, CryptoQuoteService cryptoQuoteService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.intentionService = intentionService;
        this.userInfoOperationService = userInfoOperationService;
        this.cryptoQuoteService = cryptoQuoteService;
    }
    public TransactionDto acceptIntetion(TransactionRequest transactionRequest){
        return generateTransaction(transactionRequest, "CONFIRM");
    }

    private TransactionDto generateTransaction(TransactionRequest transactionRequest, String action) {
        User user = userService.getUserByEmail(transactionRequest.getUserEmail());
        Intention intention = intentionService.getIntentionById(transactionRequest.getIdIntention());
        UserInfoOperation userInfoOperation = userInfoOperationService.findByUserId(intention.getUser().getId());
        Transaction transaction = new Transaction(intention, user);
        checkPosibleTransaction(intention, transaction, userInfoOperation, action);
        return transactionRepository.save(transaction).toDto();
    }

    private void checkPosibleTransaction(Intention intention, Transaction transaction, UserInfoOperation userInfoOperation, String action) {
        CryptoQuote cryptoQuote = cryptoQuoteService.getCryptoQuote(intention.getCrypto());
        intention.getOperation().processOperation(transaction, intention, userInfoOperation, action, getVariationPercent(intention, cryptoQuote));
        intention.setActive(false);
        this.intentionService.save(intention);
        this.userInfoOperationService.save(userInfoOperation);
    }
    private BigDecimal getVariationPercent(Intention intention, CryptoQuote cryptoQuote) {
        float variation = (Float.valueOf(cryptoQuote.getUsdPrice()) / Float.valueOf(intention.getCryptoPrice())) -1;
        return new BigDecimal(variation*100).setScale(2, RoundingMode.HALF_UP);
    }


    public List<TradedVolumeDto> getTradedVolume(Date fromDate,Date toDate) {
        List<IntentionDto> intentionDtos = intentionService.findAllIntentions();
        List<TradedVolumeDto> tradedVolumeDtos = new ArrayList<>();

        for (CryptoAssetsEnum crypto : CryptoAssetsEnum.values()) {
            TradedVolumeDto tradedVolumeDto = new TradedVolumeDto();
            tradedVolumeDto.setDateAndTime(LocalDateTime.now().toString());
            tradedVolumeDto.setCrypto(crypto.toString());
            float totalValueOperatedUSD = 0;
            float totalValueOperatedArgentinianPesos = 0;
            long totalNominalAmount = 0;


            for (IntentionDto intentiondto : intentionDtos) {
                if (intentiondto.getCrypto().equals(crypto.name()) && isInsideTimeWindow(intentiondto.getDate(), fromDate, toDate)) {
                    totalValueOperatedUSD += Float.parseFloat(intentiondto.getCryptoPrice());
                    totalValueOperatedArgentinianPesos += Float.parseFloat(intentiondto.getOperationAmountArg());
                    totalNominalAmount += intentiondto.getNominalAmount();
                }
            }

            tradedVolumeDto.setTotalNominalAmount(totalNominalAmount);
            tradedVolumeDto.setTotalValueOperatedArgentinianPesos(totalValueOperatedArgentinianPesos);
            tradedVolumeDto.setTotalValueOperatedUSD(totalValueOperatedUSD);

            tradedVolumeDto.setCurrentValueUSD(Float.parseFloat(cryptoQuoteService.getCryptoQuote(crypto.toString()).getUsdPrice())) ;
            tradedVolumeDto.setCurrentValueArgentinianPesos(Float.parseFloat(cryptoQuoteService.getCryptoQuote(crypto.toString()).getPesosPrice()));

            tradedVolumeDtos.add(tradedVolumeDto);
        }
        return tradedVolumeDtos;
    }

    private boolean isInsideTimeWindow(LocalDateTime date, Date fromDate, Date toDate) {
        LocalDateTime from = fromDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        LocalDateTime to = toDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return date.isAfter(from) && date.isBefore(to);
    }
}
