package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.services;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.exception.EntityNotFoundException;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.Intention;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.IntentionDto;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.dto.IntentionRequest;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.repositories.IntentionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntentionService {
    private final IntentionRepository intentionRepository;
    @Autowired
    public IntentionService(IntentionRepository intentionRepository) {
        this.intentionRepository = intentionRepository;
    }
    @Transactional
    public IntentionDto createIntention(User user, IntentionRequest intentionRequest) {
        Intention intention = new Intention(intentionRequest, user);
        return intentionRepository.save(intention).toDto();
    }

    public List<IntentionDto> findAllIntentionsActiveByUser(User user) {
        return intentionRepository.findAllByUserAndActive(user, true)
                .stream()
                .map(Intention::toDto).collect(Collectors.toList());
    }

    public Intention getIntentionById(String idIntention) {
        return intentionRepository.findById(idIntention).orElseThrow(()-> new EntityNotFoundException("Intention not found"));
    }
}
