package org.atividade1;

import org.atividade1.DataStructures.AVLTree.AVLTree;
import org.atividade1.DataStructures.HashTable.HashTable;
import org.atividade1.Entities.CreditCard;
import org.atividade1.Entities.Client;
import org.atividade1.Entities.Item;
import org.atividade1.Entities.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner menu = new Scanner (System.in);
        HashTable<String, Client> Clients = new HashTable<>();
        HashTable<String, CreditCard> CreditCards = new HashTable<>();

        while (true) {
        System.out.print("##-- Menu --##\n\n");
        System.out.print("|----------------------------------|\n");
        System.out.print("| 1 - Registrar cliente            |\n");
        System.out.print("| 2 - Registrar cartão de crédito  |\n");
        System.out.print("| 3 - Registrar compra             |\n");
        System.out.print("| 4 - Mostrar cliente              |\n");
        System.out.print("| 5 - Mostrar cartão               |\n");
        System.out.print("| 6 - Mostrar compra de um cartão  |\n");
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
                System.out.print("Informe o numero do cartão: ");
                String id = menu.next();
                System.out.print("Informe o nome no cartão: ");
                String ownerName = menu.next();
                System.out.print("Informe a data de expiração do cartão: ");
                String expirationDate = menu.next();


                if(Clients.get(cpf) != null) {
                    System.out.println("O cliente já existe");
                    break;
                }

                if(CreditCards.get(id) != null) {
                    System.out.println("O cartão já existe");
                    break;
                }

                try {
                    CreditCards.put(id, new CreditCard(id, ownerName, expirationDate, new AVLTree<Order>()));
                    Client client = new Client(cpf, fullName, address, new ArrayList<String>());
                    client.getRegisteredCreditCards().add(id);
                    Clients.put(cpf, client);
                } catch (Error e) {
                    System.out.print("Error: " + e.getMessage());
                }
            }
                break;

            case 2: {
                System.out.print("Informe o número do cartão: ");
                String id = menu.next();
                System.out.print("Informe o nome no cartão: ");
                String ownerName = menu.next();
                System.out.print("Informe a data de expiração do cartão: ");
                String expirationDate = menu.next();
                System.out.print("Informe o cpf do usuário: ");
                String cpf = menu.next();

                if(Clients.get(cpf) == null) {
                    System.out.println("O cliente não está registrado");
                    break;
                }

                if(CreditCards.get(id) != null) {
                    System.out.println("O cartão já existe");
                    break;
                }
                    try {
                        CreditCard creditCard = new CreditCard(id, ownerName, expirationDate, new AVLTree<Order>());
                        CreditCards.put(id, creditCard);
                    } catch (Error e) {
                        System.out.print("Error: " + e.getMessage());
                    }
                Clients.get(cpf).addRegisteredCreditCards(id);

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

                System.out.print("Informe o numero do cartão em que a compra foi realizada: ");
                String id = menu.next();

                CreditCard creditCard = CreditCards.get(id);

                if(!(Clients.get(cpf).getRegisteredCreditCards().contains(id))) {
                    System.out.println("O cartão de crédito não pertence a este usuário");
                    break;
                }

                if(creditCard == null) {
                    System.out.println("O cartão de crédito não está registrado");
                    break;
                }

                System.out.print(" -Inserindo um novo produto a compra -\n");
                int wantInsertNewItem;
                List<Item> items = new ArrayList<>();
                do {
                    System.out.print("Informe o nome do produto: ");
                    String itemName = menu.next();
                    System.out.print("Informe o valor do produto: R$");
                    Double value = menu.nextDouble();
                    try {
                        items.add(new Item(itemName, value));
                    } catch (Error e) {
                        System.out.print("Error: " + e.getMessage());
                    }
                    System.out.print("Deseja inserir um novo produto?(1-Sim/0-Não)\n");
                    wantInsertNewItem = menu.nextInt();
                } while(wantInsertNewItem != 0);

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
                    System.out.println(
                            "Nome do cliente: " + client.getFullName() + "\n" +
                            "Endereço: " + client.getAddress() + "\n" +
                            "Números dos cartões registrados: " + client.getRegisteredCreditCards()
                            );
                } catch (Error e) {
                    System.out.print("Error: " + e.getMessage());
                }
                break;
            }
            case 5: {
                System.out.print("Informe o numero do cartão de crédito: ");
                String id = menu.next();
                try {
                    CreditCard creditCard = CreditCards.get(id);
                    System.out.println(
                            "Dono do cartão: " + creditCard.getOwnerName() + "\n" +
                                    "Data de validade: " + creditCard.getExpirationDate() + "\n" +
                                    "Compras associadas: ");
                    creditCard.getOrders().traverse();
                } catch (Error e) {
                    System.out.print("Error: " + e.getMessage());
                }
                break;
            }
            case 6: {
                System.out.print("Informe o id do cartão de crédito: ");
                String id = menu.next();
                CreditCard creditCard = CreditCards.get(id);
                if(creditCard == null) {
                    System.out.println("Cartão de crédito não encontado");
                    break;
                }

                System.out.print("Informe o código da compra: ");
                String orderCode = menu.next();

                try {
                    creditCard.getOrders().traverseAndGetOrder(orderCode);
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