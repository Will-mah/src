   class Pet {
        private String petName;
        private int daysStay;
        private String petType;
        private int petAge;

        public Pet(String petName, int daysStay, String petType, int petAge) {
            this.petName = petName;
            this.daysStay = daysStay;
            this.petType = petType;
            this.petAge = petAge;
        }

        public String getPetName() {
            return petName;
        }

        public int getDaysStay() {
            return daysStay;
        }

        public String getPetType() {
            return petType;
        }

        public void setPetType(String petType) {
            this.petType = petType;
        }

        public int getPetAge() {
            return petAge;
        }

        public void setPetAge(int petAge) {
            this.petAge = petAge;


        }
}
