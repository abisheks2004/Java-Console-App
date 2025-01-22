package onlineshopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }

    public List<Product> getProducts() {
        return products;
    }
}

class Order {
    private int id;
    private Customer customer;
    private Cart cart;
    private double total;

    public Order(int id, Customer customer, Cart cart) {
        this.id = id;
        this.customer = customer;
        this.cart = cart;
        this.total = cart.calculateTotal();
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Cart getCart() {
        return cart;
    }

    public double getTotal() {
        return total;
    }
}

class Customer {
    private int id;
    private String name;
    private String email;

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

public class Shopping{
    public static void main(String[] args) {
        Customer customer = new Customer(1, "John Doe", "johndoe@example.com");
        Cart cart = new Cart();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add product to cart");
            System.out.println("2. Remove product from cart");
            System.out.println("3. View cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter product ID:");
                    int productId = scanner.nextInt();
                    System.out.println("Enter product name:");
                    String productName = scanner.next();
                    System.out.println("Enter product price:");
                    double productPrice = scanner.nextDouble();
                    System.out.println("Enter product quantity:");
                    int productQuantity = scanner.nextInt();

                    Product product = new Product(productId, productName, productPrice, productQuantity);
                    cart.addProduct(product);
                    break;
                case 2:
                    System.out.println("Enter product ID to remove:");
                    int removeProductId = scanner.nextInt();
                    for (Product p : cart.getProducts()) {
                        if (p.getId() == removeProductId) {
                            cart.removeProduct(p);
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Cart contents:");
                    for (Product p : cart.getProducts()) {
                        System.out.println(p.getName() + " - " + p.getPrice() + " x " + p.getQuantity());
                    }
                    System.out.println("Total: " + cart.calculateTotal());
                    break;
                case 4:
                    Order order = new Order(1, customer, cart);
                    System.out.println("Order placed successfully! Order ID: " + order.getId());
                    System.out.println("Total: " + order.getTotal());
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}