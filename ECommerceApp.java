import java.util.ArrayList;
import java.util.Scanner;

// Product Class
class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return id + ". " + name + " - $" + price;
    }
}

// Shopping Cart Class
class ShoppingCart {
    private ArrayList<Product> cart;

    public ShoppingCart() {
        this.cart = new ArrayList<>();
    }

    public void addProduct(Product product) {
        cart.add(product);
        System.out.println(product.getName() + " added to cart.");
    }

    public void removeProduct(int productId) {
        for (Product p : cart) {
            if (p.getId() == productId) {
                cart.remove(p);
                System.out.println(p.getName() + " removed from cart.");
                return;
            }
        }
        System.out.println("Product not found in cart.");
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("Your Cart:");
        double total = 0;
        for (Product p : cart) {
            System.out.println(p);
            total += p.getPrice();
        }
        System.out.println("Total: $" + total);
    }

    public double checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add items before checkout.");
            return 0;
        }
        double total = 0;
        for (Product p : cart) {
            total += p.getPrice();
        }
        cart.clear();
        return total;
    }
}

// Main Class
public class ECommerceApp {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ShoppingCart cart = new ShoppingCart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeProducts();
        while (true) {
            System.out.println("\n--- E-Commerce App ---");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> displayProducts();
                case 2 -> addToCart();
                case 3 -> removeFromCart();
                case 4 -> cart.viewCart();
                case 5 -> {
                    double total = cart.checkout();
                    if (total > 0) {
                        System.out.println("Order placed successfully! Total Amount: $" + total);
                    }
                }
                case 6 -> {
                    System.out.println("Thank you for shopping with us! Goodbye.");
                    return;
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }

    private static void initializeProducts() {
        products.add(new Product(1, "Laptop", 800.00));
        products.add(new Product(2, "Smartphone", 500.00));
        products.add(new Product(3, "Headphones", 50.00));
        products.add(new Product(4, "Smartwatch", 150.00));
        products.add(new Product(5, "Gaming Mouse", 30.00));
    }

    private static void displayProducts() {
        System.out.println("\nAvailable Products:");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private static void addToCart() {
        displayProducts();
        System.out.print("Enter product ID to add to cart: ");
        int productId = scanner.nextInt();
        for (Product p : products) {
            if (p.getId() == productId) {
                cart.addProduct(p);
                return;
            }
        }
        System.out.println("Invalid product ID.");
    }

    private static void removeFromCart() {
        cart.viewCart();
        System.out.print("Enter product ID to remove from cart: ");
        int productId = scanner.nextInt();
        cart.removeProduct(productId);
    }
}
