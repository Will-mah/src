class Cat extends Pet {
    private int catSpaceNumber;

    public Cat(String petName, int daysStay, int petAge, int catSpaceNumber) {
        super(petName, daysStay, "Cat", petAge);
        this.catSpaceNumber = catSpaceNumber;
    }

    public int getCatSpaceNumber() {
        return catSpaceNumber;
    }

    public void setCatSpaceNumber(int catSpaceNumber) {
        this.catSpaceNumber = catSpaceNumber;
    }
}