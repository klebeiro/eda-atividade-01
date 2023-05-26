package org.atividade1.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.atividade1.DataStructures.RBT.RedBlackTree;

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
    List<Integer> registeredCreditCards;

    public void addRegisteredCreditCards(CreditCard creditCard) {
        registeredCreditCards.add(creditCard.getId());
    }
}
