package SayHello;

public class BasePerson implements Person {

    private String name;

    public BasePerson(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String sayHello() {
        System.out.println("Hello");
        return "Hello";
    }

    public void setName(String name) {
        this.name = name;
    }
}
