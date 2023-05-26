package org.atividade1.Entities;

import lombok.Data;
import lombok.NonNull;
import org.atividade1.DataStructures.AVLTree.AVLTree;

@Data
public class CreditCard {
    @NonNull
    String cardNumber;
    @NonNull
    String ownerName;
    @NonNull
    String expirationDate;
    @NonNull
    AVLTree<Order> orders;
}