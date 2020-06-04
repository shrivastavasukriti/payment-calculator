package com.feecalculator.model;

public enum FEE {
// TODO : Check for bigDecimal in Enum
        FIVE_HUNDRED(500), HUNDRED(100), FIFTY(50), TEN(10);
        private double fees;

        FEE(double fees) {
            this.fees = fees;
        }

        public double getFees() {
            return fees;
        }
}
