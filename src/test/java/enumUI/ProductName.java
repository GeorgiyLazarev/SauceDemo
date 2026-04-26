package enumUI;

public enum ProductName {
    BACKPACK("Sauce Labs Backpack"),
    FLASHLIGHT("Sauce Labs Bike Light"),
    BLACK_SHIRT("Sauce Labs Bolt T-Shirt"),
    FLEECE_JACKET("Sauce Labs Fleece Jacket"),
    CHILDREN_PAJAMAS("Sauce Labs Onesie"),
    RED_JACKET("Test.allTheThings() T-Shirt (Red)");

    private final String displayName;

    ProductName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
