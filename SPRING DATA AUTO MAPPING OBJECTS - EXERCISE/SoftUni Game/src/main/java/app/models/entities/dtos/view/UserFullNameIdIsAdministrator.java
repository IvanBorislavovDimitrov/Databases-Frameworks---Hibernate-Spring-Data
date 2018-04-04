package app.models.entities.dtos.view;

public class UserFullNameIdIsAdministrator {

    private Long id;
    private String fullName;
    private boolean isAdministrator;

    public UserFullNameIdIsAdministrator() {
    }

    public UserFullNameIdIsAdministrator(Long id, String fullName, boolean isAdministrator) {
        this.id = id;
        this.fullName = fullName;
        this.isAdministrator = isAdministrator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }
}
