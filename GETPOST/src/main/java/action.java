import com.opensymphony.xwork2.Action;

public class action implements Action {
    String name;
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotadataally() {
        return Motadataally;
    }

    public void setMotadataally(String motadataally) {
        Motadataally = motadataally;
    }

    String Motadataally;


    @Override
    public String execute() throws Exception {

        database.insert(this);

        return SUCCESS;
    }
}