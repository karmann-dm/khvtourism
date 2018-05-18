package com.tourism.khvtourism.service;

import com.tourism.khvtourism.model.Card;
import com.tourism.khvtourism.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }

    @Override
    public Card getCard(Long id) {
        return cardRepository.findById(id).get();
    }
}
