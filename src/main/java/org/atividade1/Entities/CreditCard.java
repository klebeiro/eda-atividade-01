package org.atividade1.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NonNull;
import org.atividade1.DataStructures.AVLT.AVLTree;
import org.atividade1.DataStructures.RBT.RedBlackTree;

import java.util.Date;

@Data
public class CreditCard {
    @NonNull
    Integer id;
    @NonNull
    String ownerName;
    @NonNull
    String expirationDate;
    @NonNull
    AVLTree<Order> orders;
}