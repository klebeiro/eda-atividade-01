package org.atividade1.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
public class Client {
    @NonNull
    String cpf;
    @NonNull
    String fullName;
    @NonNull
    String address;
    @NonNull
    List<String> registeredCreditCards;

    public void addRegisteredCreditCards(String cardNumber) {
        registeredCreditCards.add(cardNumber);
    }
}
