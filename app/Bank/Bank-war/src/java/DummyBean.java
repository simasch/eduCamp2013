
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class DummyBean implements Serializable {

    private String name;
    private String output;

    public String doIt() {
        try {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("error", "es ist ein Fehler aufgetreten"));
            this.output = name;
            return "result.xhtml";
        } catch (Exception e) {
            return "error";
        }
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
