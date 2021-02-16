package eu.vmpay.random.city.tools;

public class ColorUtils {
    public static int getFixedColor(String receiver) {
        switch (receiver) {
            case "Yellow":
                return FixedColors.YELLOW.getColor();
            case "Green":
                return FixedColors.GREEN.getColor();
            case "Blue":
                return FixedColors.BLUE.getColor();
            case "Black":
                return FixedColors.BLACK.getColor();
            case "Red":
                return FixedColors.RED.getColor();
            case "White":
                return FixedColors.WHITE.getColor();
            default:
                return FixedColors.UNKNOWN.getColor();
        }
    }
}
