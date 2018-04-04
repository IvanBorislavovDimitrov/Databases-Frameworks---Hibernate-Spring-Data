package app.models.entities.dtos.view;

public class UserFullNameWithId {

    private String fullName;
    private Long id;

    public UserFullNameWithId() {
    }

    public UserFullNameWithId(String fullName, Long id) {
        this.fullName = fullName;
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
