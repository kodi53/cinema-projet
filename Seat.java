package film2;


class Seat {
    private String seatID;
    private boolean isReserved;
    private boolean isSelected;

    public Seat(String seatID, boolean isReserved) {
        this.seatID = seatID;
        this.isReserved = isReserved;
        this.isSelected = false;
    }

    public String getSeatID() { return seatID; }
    public boolean isReserved() { return isReserved; }
    public boolean isSelected() { return isSelected; }

    public void toggleSelection() {
        if (!isReserved) {
            isSelected = !isSelected;
        }
    }
}