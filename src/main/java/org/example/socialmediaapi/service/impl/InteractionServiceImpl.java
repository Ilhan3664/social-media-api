package org.example.socialmediaapi.service.impl;

import org.example.socialmediaapi.constants.Status;
import org.example.socialmediaapi.dto.request.InteractionRequest;
import org.example.socialmediaapi.dto.response.InteractionResponse;
import org.example.socialmediaapi.entity.Interaction;
import org.example.socialmediaapi.mappers.InteractionMapper;
import org.example.socialmediaapi.repository.InteractionRepository;
import org.example.socialmediaapi.service.AbstractService;
import org.example.socialmediaapi.service.InteractionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class InteractionServiceImpl extends AbstractService implements InteractionService {

    private final InteractionRepository interactionRepository;
    private final InteractionMapper interactionMapper;

    public InteractionServiceImpl(InteractionRepository interactionRepository, InteractionMapper interactionMapper) {
        this.interactionRepository = interactionRepository;
        this.interactionMapper = interactionMapper;
    }

    @Override
    @Transactional
    public InteractionResponse save(InteractionRequest request) {
        Interaction interaction = interactionMapper.requestToInteraction(request);
        interaction.setStatus(1);
        interaction.setCreateDate(new Date());
        interactionRepository.save(interaction);
        return interactionMapper.interactionToResponse(interaction);
    }

    @Override
    @Transactional
    public InteractionResponse update(Long id, InteractionRequest newInfo) {
        Interaction oldInteraction = interactionRepository.getById(id);
        Interaction newInteraction = interactionMapper.requestToInteraction(newInfo);
        if(oldInteraction.getType() == 0) {
            oldInteraction.setContext(newInteraction.getContext());
            oldInteraction.setUpdateDate(new Date());
            interactionRepository.save(oldInteraction);
        }
        return interactionMapper.interactionToResponse(oldInteraction);
    }

    @Override
    @Transactional
    public InteractionResponse delete(Long id) {
        Interaction interaction = interactionRepository.getById(id);
        interaction.setStatus(0);
        interaction.setUpdateDate(new Date());
        interactionRepository.save(interaction);
        return interactionMapper.interactionToResponse(interaction);
    }

    @Override
    public InteractionResponse getById(Long id) {
        Interaction interaction = interactionRepository.findByAccountIdAndStatus(id, Status.ACTIVE.getValue());
        return interactionMapper.interactionToResponse(interaction);
    }

    @Override
    public List<InteractionResponse> getAll() {
        List<Interaction> interactions = interactionRepository.findAllByStatus(Status.ACTIVE.getValue());
        return interactionMapper.interactionsToResponses(interactions);
    }

    @Override
    public List<InteractionResponse> getByType(int type) {
        List<Interaction> interactions = interactionRepository.findAllByType(type);
        return interactionMapper.interactionsToResponses(interactions);
    }

    @Override
    public List<Interaction> getAllByPostIdAndType(int postId, int type) {
        return interactionRepository.findAllByPostIdAndType(postId, type);
    }
}
