package SayHello;

public class Chinese extends BasePerson {

    private String name;

    public Chinese(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String sayHello() {
        System.out.println("Djydjybydjy");
        return "Djydjybydjy";
    }
}
