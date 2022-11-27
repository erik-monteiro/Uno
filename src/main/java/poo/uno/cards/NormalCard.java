package poo.uno.cards;

public class NormalCard extends Card {

    public enum Color {
        BLUE   { public String toString() { return "BLUE"; }},
        RED    { public String toString() { return "RED"; }},
        GREEN  { public String toString() { return "GREEN"; }},
        YELLOW { public String toString() { return "YELLOW"; }}
    }

    private Color color;

    public NormalCard(String anId, String anImageId, int val, Color color) {
        super(anId, anImageId, val);
        this.color = color;
    }

    public String getColor() {
        return color.toString();
    }
    
    public boolean haveTheSameColor(NormalCard outra) {
        if (this.getColor().equals(outra.getColor()))
            return true;
        return false;
    }

}
