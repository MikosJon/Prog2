public class PoudarekNizov {
    public static void main(String[] args){
        System.out.println(poudari("Zadnje Novice"));
        System.out.println(poudariDel("Zadnje *Novice* danes"));
    }

    public static String poudari(String s) {
        String out = "";
        for (int i = 0; i < s.length(); ++i) {
            char znak = s.charAt(i);
            char velikZnak = Character.toUpperCase(znak);
            if (i == 0) out += velikZnak;
            else out += " " + velikZnak;
        }
        return out;
    }

    public static String poudariDel(String s) {
        String out = "";
        boolean inside = false;
        for (int i = 0; i < s.length(); ++i) {
            char znak = s.charAt(i);
            if (znak == '*') inside = !inside;
            else if (inside) out += Character.toUpperCase(znak);
            else out += znak;
        }
        return out;
    }
}
