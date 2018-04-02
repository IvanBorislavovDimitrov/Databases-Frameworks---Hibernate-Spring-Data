import java.util.*;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String url = input.nextLine();

        String protocol = "\"\"";
        if (url.contains("://")) {
            protocol = "\"" +  url.substring(0, url.indexOf("://")) + "\"";
        }

        int indexOfProtocol = url.indexOf("://");
        if (indexOfProtocol == -1) {
            indexOfProtocol = 0;
        } else {
            indexOfProtocol += 3;
        }
        String server = "\"" +  url.substring(indexOfProtocol,
                url.indexOf("/", indexOfProtocol) == -1 ? url.length() : url.indexOf("/", indexOfProtocol)) + "\"";

        String resource = "\"\"";
        if (url.indexOf("/", url.indexOf("://") + 3) != -1) {
            resource = "\"" + url.substring(url.indexOf("/", url.indexOf("://") + 3) + 1) + "\"";
        }


        System.out.println("[protocol] = " + protocol);
        System.out.println("[server] = " + server);
        System.out.println("[resource] = " + resource);
    }
}
