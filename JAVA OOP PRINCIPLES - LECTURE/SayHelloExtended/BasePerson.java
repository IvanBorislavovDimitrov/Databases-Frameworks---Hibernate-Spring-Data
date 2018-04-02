package SayHello;

public abstract class BasePerson implements Person {

    private String name;

    public BasePerson(String name) {
        this.name = name;
    }

    public abstract String getName();

    public abstract String sayHello();

    public void setName(String name) {
        this.name = name;
    }
}
