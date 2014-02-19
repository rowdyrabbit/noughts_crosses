package au.com.denisefernandez;


public enum BoardSymbol {
    X, O, Empty {
        @Override
        public String toString() {
            return " ";
        }
    }

}
