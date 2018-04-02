package animals;

public class Animal {

    private String name;
    private int age;
    private String gender;

    protected String produceSound() {
        return "Not implemented!";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender == null || gender.equals("")) {
            throw new IllegalArgumentException("Invalid input!");
        } else if (! gender.equals("Male") && ! gender.equals("Female")) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("%s %d %s%n%s", this.name,this.age,this.gender,this.produceSound());
    }
}
