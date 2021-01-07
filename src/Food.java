public class Food {

    private String name;
    private double cals;

    public Food(String name, double cals) {
        this.name = name;
        this.cals = cals;
    }

    public String getName() {
        return name;
    }

    public double getCals() {
        return cals;
    }

    public void setName(String s) {
        this.name = s;
    }

    public void setCals(double cals) {
        this.cals = cals;
    }

    public String toString() {
        return this.name + " contains " + this.cals + " calories";
    }


}
