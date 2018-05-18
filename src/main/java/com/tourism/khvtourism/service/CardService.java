package com.tourism.khvtourism.service;

import com.tourism.khvtourism.model.Card;

public interface CardService {
    void saveCard(Card card);
    Card getCard(Long id);
}
