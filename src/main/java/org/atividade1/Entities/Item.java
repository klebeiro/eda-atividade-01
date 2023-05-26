package org.atividade1.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Item {
    @NonNull
    String itemName;
    @NonNull
    double price;
}
