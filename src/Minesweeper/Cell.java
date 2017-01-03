package Minesweeper;

class Cell {

    private static final String UNICODE_WHITE_SQUARE_WITH_ROUNDED_CORNERS = "\u25a2";
    private static final String UNICODE_FISHEYE = "\u25c9";

    private boolean isBomb = false;
    private boolean hasBeenPicked = false;
    private String value;

    Cell(){
        value = UNICODE_WHITE_SQUARE_WITH_ROUNDED_CORNERS;
    }

    boolean getHasBeenPicked() {
        return this.hasBeenPicked;
    }

    boolean getIsBomb() {
        return this.isBomb;
    }

    String getValue() {
        return this.value;
    }

    void setHasBeenPicked(boolean hasBeenPicked) {
        this.hasBeenPicked = hasBeenPicked;
    }

    void setIsBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return (getHasBeenPicked() && getIsBomb())? UNICODE_FISHEYE : getValue();
    }
}
