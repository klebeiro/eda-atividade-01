package org.atividade1.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
public class Order implements Comparable<Order> {
    @NonNull
    String orderCode;
    @NonNull
    Client client;
    @NonNull
    List<Item> itemList;
    @NonNull
    Double value;

    @Override
    public int compareTo(Order order) {
        return order.orderCode.compareTo(orderCode);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderCode='" + orderCode + '\'' +
                ", client=" + client +
                ", itemList=" + itemList +
                ", value=" + value +
                '}';
    }
}
