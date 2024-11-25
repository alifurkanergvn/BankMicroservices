package com.afe.cards.service;

import com.afe.cards.dto.CardsDto;

public interface ICardsService {
    /**
     * @param mobileNumber  - Mobile Number of the Customer
     */
    void createCard(String mobileNumber);

    /**
     * @param mobileNumber - Input mobile number
     * @return Card Details based on given mobile number
     */
    CardsDto fetchCard(String mobileNumber);

    /**
     *
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateCard(CardsDto cardsDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     */
    boolean deleteCard(String mobileNumber);

}
