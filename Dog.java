class Dog extends Pet {
    private double dogWeight;
    private boolean groomingRequested;
    private int dogSpaceNumber;

    public Dog(String petName, int daysStay, double dogWeight, boolean groomingRequested, int petAge, int dogSpaceNumber) {
        super(petName, daysStay, "Dog", petAge);
        this.dogWeight = dogWeight;
        this.groomingRequested = groomingRequested;
        this.dogSpaceNumber = dogSpaceNumber;
    }

    public double getDogWeight() {
        return dogWeight;
    }

    public void setDogWeight(double dogWeight) {
        this.dogWeight = dogWeight;
    }

    public boolean isGroomingRequested() {
        return groomingRequested;
    }

    public void setGroomingRequested(boolean groomingRequested) {
        this.groomingRequested = groomingRequested;
    }

    public int getDogSpaceNumber() {
        return dogSpaceNumber;
    }

    public void setDogSpaceNumber(int dogSpaceNumber) {
        this.dogSpaceNumber = dogSpaceNumber;
    }
}