package co.pablopez.Model;

public class Transaction {
    public String originUser;
    public String destinationUser;
    public double amount;
    public String description;

    public Transaction() {
    }

    public Transaction(String originUser, String destinationUser, double amount, String description) {
        this.originUser = originUser;
        this.destinationUser = destinationUser;
        this.amount = amount;
        this.description = description;
    }

    public String getOriginUser() {
        return originUser;
    }

    public void setOriginUser(String originUser) {
        this.originUser = originUser;
    }

    public String getDestinationUser() {
        return destinationUser;
    }

    public void setDestinationUser(String destinationUser) {
        this.destinationUser = destinationUser;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction [originUser=" + originUser + ", destinationUser=" + destinationUser + ", amount=" + amount
                + ", description=" + description + "]";
    }
}
