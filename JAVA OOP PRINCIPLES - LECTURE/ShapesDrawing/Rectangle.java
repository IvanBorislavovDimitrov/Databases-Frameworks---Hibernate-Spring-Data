package shapes_drawing;

public class Rectangle implements Drawable {

    private Integer width;
    private Integer height;

    public Rectangle(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        for (int i = 0; i < height; i++) {
            if (i == 0 || i == height - 1) {
                System.out.println(repeatString("* ", width));
            } else {
                System.out.println(makeString(width));
            }
        }
    }

    private static String makeString(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i == 0 || i == count - 1) {
                sb.append("* ");
            } else {
                sb.append("  ");
            }
        }

        return sb.toString();
    }

    private static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }

        return sb.toString();
    }
}
