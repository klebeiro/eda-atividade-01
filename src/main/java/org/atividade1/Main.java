package org.atividade1;

import org.atividade1.DataStructures.AVLT.AVLTree;
import org.atividade1.DataStructures.HashTable.HashTable;
import org.atividade1.DataStructures.RBT.RedBlackTree;
import org.atividade1.Entities.CreditCard;
import org.atividade1.Entities.Client;
import org.atividade1.Entities.Item;
import org.atividade1.Entities.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BinaryOperator;

public class Main {

    public static void main(String[] args) {
        Scanner menu = new Scanner (System.in);
        HashTable<String, Client> Clients = new HashTable<>();
        HashTable<Integer, CreditCard> CreditCards = new HashTable<>();

        while (true) {
        System.out.print("##-- Menu --##\n\n");
        System.out.print("|----------------------------------|\n");
        System.out.print("| 1 - Registrar cliente            |\n");
        System.out.print("| 2 - Registrar cartão de crédito  |\n");
        System.out.print("| 3 - Registrar compra             |\n");
        System.out.print("| 4 - Mostrar cliente              |\n");
        System.out.print("| 5 - Mostrar cartão               |\n");
        System.out.print("| 6 - Mostrar compras de um cartão |\n");
        System.out.print("| 7 - Sair                         |\n");
        System.out.print("|----------------------------------|\n");
        System.out.print("Opção: ");

        int option = menu.nextInt();


        switch (option) {
            case 1: {
                System.out.print("Informe o cpf do cliente: ");
                String cpf = menu.next();
                System.out.print("Informe o nome do cliente: ");
                String fullName = menu.next();
                System.out.print("Informe o endereço do cliente: ");
                String address = menu.next();
                System.out.print("Informe o id do cartão de crédito: ");
                Integer id = menu.nextInt();

                if(CreditCards.get(id) == null) {
                    System.out.println("O cartão não foi registrado");
                    break;
                }

                try {
                    Client client = new Client(cpf, fullName, address, new ArrayList<Integer>());
                    client.getRegisteredCreditCards().add(id);
                    Clients.put(cpf, client);
                } catch (Error e) {
                    System.out.print("Error: " + e.getMessage());
                }
            }
                break;

            case 2: {
                System.out.print("Informe o id do cartão: ");
                Integer id = menu.nextInt();
                System.out.print("Informe o nome do dono do cartão: ");
                String ownerName = menu.next();
                System.out.print("Informe a data de expiração do cartão: ");
                String expirationDate = menu.next();
                System.out.print("Informe o cpf do usuário: ");
                String cpf = menu.next();

                if(Clients.get(cpf) == null) {
                    System.out.println("O cliente não está registrado");
                    break;
                }

                try {
                    CreditCard creditCard = new CreditCard(id, ownerName, expirationDate, new AVLTree<Order>());
                    CreditCards.put(id, creditCard);
                    Clients.get(cpf).addRegisteredCreditCards(creditCard);
                } catch (Error e) {
                    System.out.print("Error: " + e.getMessage());
                }
                break;
            }
            case 3: {
                System.out.print("Informe o código da compra: ");
                String orderCode = menu.next();
                System.out.print("Informe o cpf do cliente que realizou a compra: ");
                String cpf = menu.next();
                if(Clients.get(cpf) == null) {
                    System.out.println("O cliente não está registrado");
                    break;
                }
                System.out.print(" -Inserindo um novo produto a compra -\n");
                Integer wantInsertNewItem;
                List<Item> items = new ArrayList<>();
                do {
                    System.out.print("Informe o nome do produto: ");
                    String itemName = menu.next();
                    System.out.print("Informe o valor do produto: ");
                    Double value = menu.nextDouble();
                    try {
                        items.add(new Item(itemName, value));
                    } catch (Error e) {
                        System.out.print("Error: " + e.getMessage());
                    }
                    wantInsertNewItem = menu.nextInt();
                } while(wantInsertNewItem != 0);

                System.out.print("Informe o id do cartão em que a compra foi realizada: ");
                Integer id = menu.nextInt();

                CreditCard creditCard = CreditCards.get(id);

                if(creditCard == null) {
                    System.out.println("O cartão de crédito não está registrado");
                    break;
                }

                double totalPrice = 0.0;

                try {
                    Order order = new Order(
                            orderCode,
                            Clients.get(cpf),
                            items,
                            items.stream()
                                    .mapToDouble(Item::getPrice)
                                    .reduce(0.0, Double::sum)
                            );
                    CreditCards.get(id).getOrders().insert(order);
                } catch (Error e) {
                    System.out.print("Error: " + e.getMessage());
                }
                break;
            }
            case 4: {
                System.out.print("Informe o cpf do cliente: ");
                String cpf = menu.next();
                try {
                    Client client = Clients.get(cpf);
                    System.out.println(client.toString());
                } catch (Error e) {
                    System.out.print("Error: " + e.getMessage());
                }
                break;
            }
            case 5: {
                System.out.print("Informe o id do cartão de crédito: ");
                Integer id = menu.nextInt();
                try {
                    CreditCard creditCard = CreditCards.get(id);
                    System.out.println(creditCard.toString());
                } catch (Error e) {
                    System.out.print("Error: " + e.getMessage());
                }
                break;
            }
            case 6: {
                try {
                    System.out.print("Informe o id do cartão de crédito: ");
                    Integer id = menu.nextInt();
                    CreditCard creditCard = CreditCards.get(id);
                    if(creditCard == null) {
                        System.out.println("Cartão de crédito não encontado");
                        break;
                    }
                    creditCard.getOrders().traverse();
                } catch (Error e) {
                    System.out.print("Error: " + e.getMessage());
                }
                break;
            }

            default:
                System.out.print("\nOpção Inválida!");
                break;

            case 7:
                System.out.print("\nAté logo!");
                menu.close();
            }
        }
    }
}