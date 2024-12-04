import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private String name;
    private double price;
    private int quantity;
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    public String getPriceInINR() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return "₹" + df.format(price);
    }
}

class ShoppingCart {
    private ArrayList<Product> items;
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("Your Shopping Cart:");
        double total = 0;
        for (Product item : items) {
            System.out.println("Product: " + item.getName() + ", Price: " + item.getPriceInINR() + ", Quantity: " + item.getQuantity());
            total += item.getTotalPrice();
        }
        System.out.println("Total: ₹" + total);
    }
}

class OnlineShoppingSystem {
    private ArrayList<Product> products;
    private ShoppingCart cart;
    public OnlineShoppingSystem() {
        this.products = new ArrayList<>();
        this.cart = new ShoppingCart();
        // Adding sample products
        products.add(new Product("Laptop", 69999.0, 5));
        products.add(new Product("Smartphone", 44999.0, 10));
        products.add(new Product("Headphones", 2999.0, 20));
    }

    public void displayProducts() {
        System.out.println("Available Products:");
        for (Product product : products) {
            System.out.println("Product: " + product.getName() + ", Price: " + product.getPriceInINR() + ", Available Quantity: " + product.getQuantity());
        }
    }

    public void addToCart(String productName, int quantity) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                if (product.getQuantity() >= quantity) {
                    cart.addItem(new Product(product.getName(), product.getPrice(), quantity));
                    product.setQuantity(product.getQuantity() - quantity);
                    System.out.println(quantity + " " + productName + "(s) added to cart.");
                } else {
                    System.out.println("Insufficient quantity of " + productName + " in stock.");
                }
                return;
            }
        }
        System.out.println("Product " + productName + " not found.");
    }

    public void viewCart() {
        cart.displayCart();
    }

    public void buy() {
        System.out.println("\nReceipt:");
        cart.displayCart();
        System.out.println("Thank you for purchasing from us!");
    }
}

public class shopping_system_project {
    public static void main(String[] args) {
        OnlineShoppingSystem system = new OnlineShoppingSystem();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to the Online Shopping System");
            System.out.println("1. Display Available Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Buy");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    system.displayProducts();
                    break;
                case 2:
                    System.out.print("Enter the name of the product: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter the quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    system.addToCart(productName, quantity);
                    break;
                case 3:
                    system.viewCart();
                    break;
                case 4:
                    system.buy();
                    break;
                case 5:
                    System.out.println("Exiting Online Shopping System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }

    }
}