import java.io.Serializable;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;

public class FoodsMap implements Serializable {

    private HashMap<LocalDate, ArrayList<Food>> allDays;

    public FoodsMap() {
        allDays = new HashMap<>();
    }

    public HashMap<LocalDate, ArrayList<Food>> getAllDays() {
        return this.allDays;
    }

    public int getTotalForDay(LocalDate date) throws InvalidDateException {
        if (allDays.containsKey(date)) {
            int total = 0;
            for (Food f : allDays.get(date)) {
                total += f.getCals();
            }

            return total;
        }

        else {
            throw new InvalidDateException("No entry for the date exists");
        }
    }
}



class InvalidDateException extends Exception {
    public InvalidDateException(String errorMessage) {
        super(errorMessage);
    }
}