package com.emmanuel.inventory;

/**
 * Represents a single item in the inventory system.
 * <p>
 * This class acts as a Model (or DTO), encapsulating all the essential attributes
 * of a product such as its unique identifier, descriptive name, category, and unit cost.
 * </p>
 *
 * @author Emmanuel Alcalá
 * @version 1.0
 */
public class Product {

    // Attributes
    // We use explicit Javadoc on fields to describe constraints

    /** Unique identifier for the product (e.g., "A001"). Cannot be null or empty. */
    private String id;

    /** Display name of the product. */
    private String name;

    /** Category or type (e.g., "Electronics", "Food"). */
    private String type;

    /** Unit cost of the product. Must be non-negative. */
    private double cost;

    /**
     * Constructs a new Product with the specified details.
     * * @param id   The unique identifier.
     * @param name The product name.
     * @param type The product category.
     * @param cost The cost per unit.
     * @throws IllegalArgumentException If the cost is negative.
     */
    public Product(String id, String name, String type, double cost) {
        // We use the setters inside the constructor to reuse validation logic!
        this.setId(id);
        this.name = name;
        this.type = type;
        this.setCost(cost);
    }

    // Getters and Setters

    /**
     * Gets the unique ID.
     * @return The product ID string.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the product ID.
     * * @param id The new ID.
     * @throws IllegalArgumentException if the ID is null or empty.
     */
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }
        this.id = id;
    }

    /**
     * Gets the product name.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the product type/category.
     * @return The type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the product category.
     * @param type The new type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the unit cost.
     * @return The cost as a double.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the product cost.
     * <p>
     * Validates that the cost is not negative.
     * </p>
     * * @param cost The new cost (must be >= 0).
     * @throws IllegalArgumentException if the provided cost is negative.
     */
    public void setCost(double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
        this.cost = cost;
    }

    /**
     * Returns a string representation of the product suitable for console display.
     * <p>
     * Uses fixed-width formatting to ensure columns align in lists.
     * </p>
     * * @return A formatted string (e.g., "ID: 001 | Name: Apple...").
     */
    @Override
    public String toString() {
        // %-10s means: String taking up 10 spaces, aligned to the left.
        // %.2f means: Floating point number with exactly 2 decimals.
        return String.format("ID: %-8s | Name: %-15s | Type: %-12s | Cost: $%.2f",
                id, name, type, cost);
    }
}