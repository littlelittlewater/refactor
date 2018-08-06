import java.util.Vector;

/**
 * Created by Administrator on 2018/8/6.
 */
public class Customer {
    private String name;
    private Vector<Rental> rentals = new Vector();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.addElement(rental);

    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for" + getName() + "\n";
        for (Rental rental : rentals) {
            double thisAmount = 0;
            switch (rental.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (rental.getDaysRented() > 2) thisAmount += (rental.getDaysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += rental.getDaysRented() * 3;
                    break;
                case Movie.CHILDREN:
                    thisAmount += 1.5;
                    if (rental.getDaysRented() > 3) thisAmount += (rental.getDaysRented() - 3) * 1.5;
            }
            frequentRenterPoints++;
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1)
                frequentRenterPoints++;
            totalAmount += thisAmount;
        }
        result += "Amount owed is " + totalAmount + "You earned" + frequentRenterPoints + "points";
        return result;
    }

    public String getName() {
        return name;
    }
}
