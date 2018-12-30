package pipes_client_app.dialogs;

public class DialogObject {
    private String title;
    private String message;
    private String buttonText;

    public DialogObject(String title, String message, String buttonText) {
        this.title = title;
        this.message = message;
        this.buttonText = buttonText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }
}
