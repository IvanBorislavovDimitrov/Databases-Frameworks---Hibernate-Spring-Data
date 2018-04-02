package telephony;

public class MySmartphone implements Smartphone {
    @Override
    public void browseInWww(String url) {
        String[] allUrls = url.split("\\s+");
        for (String currUrl : allUrls) {
            if (! isUrlValid(currUrl)) {
                System.out.println("Invalid URL!");
            } else {
                System.out.println(String.format("Browsing: %s!", currUrl));
            }
        }
    }

    @Override
    public void call(String receiver) {
        String[] allNumbers = receiver.split("\\s+");
        for (String phoneNumber : allNumbers) {
            if (! isNumberValid(phoneNumber)) {
                System.out.println("Invalid number!");
            } else {
                System.out.println(String.format("Calling... %s", phoneNumber));
            }
        }
    }

    private boolean isNumberValid(String phoneNumber) {
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (! Character.isDigit(phoneNumber.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private boolean isUrlValid(String url) {
        for (int i = 0; i < url.length(); i++) {
            if (Character.isDigit(url.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
