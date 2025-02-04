import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Stock {
    private String name;
    private double price;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class Portfolio {
    private Map<String, Integer> stocks = new HashMap<>();
    private double cashBalance;

    public Portfolio(double initialCashBalance) {
        this.cashBalance = initialCashBalance;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void addCash(double amount) {
        this.cashBalance += amount;
    }

    public void buyStock(String stockName, int quantity, double stockPrice) {
        double totalCost = stockPrice * quantity;
        if (totalCost <= cashBalance) {
            stocks.put(stockName, stocks.getOrDefault(stockName, 0) + quantity);
            cashBalance -= totalCost;
            System.out.println("Successfully bought " + quantity + " shares of " + stockName);
        } else {
            System.out.println("Insufficient funds to buy " + quantity + " shares of " + stockName);
        }
    }

    public void sellStock(String stockName, int quantity, double stockPrice) {
        if (stocks.containsKey(stockName) && stocks.get(stockName) >= quantity) {
            double totalSale = stockPrice * quantity;
            stocks.put(stockName, stocks.get(stockName) - quantity);
            cashBalance += totalSale;
            System.out.println("Successfully sold " + quantity + " shares of " + stockName);
        } else {
            System.out.println("You do not have enough shares to sell.");
        }
    }

    public void displayPortfolio() {
        System.out.println("\n--- Portfolio ---");
        System.out.println("Cash Balance: $" + cashBalance);
        System.out.println("Stocks Owned: ");
        if (stocks.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {
            for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
            }
        }
    }

    public double calculatePortfolioValue(Map<String, Stock> marketData) {
        double portfolioValue = cashBalance;
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            String stockName = entry.getKey();
            int quantity = entry.getValue();
            if (marketData.containsKey(stockName)) {
                portfolioValue += marketData.get(stockName).getPrice() * quantity;
            }
        }
        return portfolioValue;
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating some stocks in the market
        Map<String, Stock> marketData = new HashMap<>();
        marketData.put("AAPL", new Stock("AAPL", 150.00));
        marketData.put("GOOGL", new Stock("GOOGL", 2700.00));
        marketData.put("AMZN", new Stock("AMZN", 3400.00));

        // Initialize user portfolio with an initial cash balance
        Portfolio portfolio = new Portfolio(10000.00); // Starting with $10,000

        // Menu options
        while (true) {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Portfolio Performance");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // View market data
                    System.out.println("\n--- Market Data ---");
                    for (Stock stock : marketData.values()) {
                        System.out.println(stock.getName() + ": $" + stock.getPrice());
                    }
                    break;

                case 2:
                    // Buy stocks
                    System.out.print("Enter stock name to buy: ");
                    String buyStockName = scanner.nextLine().toUpperCase();
                    if (marketData.containsKey(buyStockName)) {
                        System.out.print("Enter quantity to buy: ");
                        int buyQuantity = scanner.nextInt();
                        portfolio.buyStock(buyStockName, buyQuantity, marketData.get(buyStockName).getPrice());
                    } else {
                        System.out.println("Invalid stock name.");
                    }
                    break;

                case 3:
                    // Sell stocks
                    System.out.print("Enter stock name to sell: ");
                    String sellStockName = scanner.nextLine().toUpperCase();
                    if (marketData.containsKey(sellStockName)) {
                        System.out.print("Enter quantity to sell: ");
                        int sellQuantity = scanner.nextInt();
                        portfolio.sellStock(sellStockName, sellQuantity, marketData.get(sellStockName).getPrice());
                    } else {
                        System.out.println("Invalid stock name.");
                    }
                    break;

                case 4:
                    // View portfolio
                    portfolio.displayPortfolio();
                    break;

                case 5:
                    // View portfolio performance
                    double portfolioValue = portfolio.calculatePortfolioValue(marketData);
                    System.out.println("\nPortfolio Value: $" + portfolioValue);
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting Stock Trading Platform...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
