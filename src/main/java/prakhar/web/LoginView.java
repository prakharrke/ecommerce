package prakhar.web;

import java.util.Map;

public class LoginView extends AbstractView {

    private static final Map<String, String> ERROR_MAP = Map.of(
            "invalid.credentials", "Invalid Credentials. Please try again.",
            "unregistered", "You are not registered with us. Please Sign Up"
    );

    private String message;
    private LoginView view;

    public LoginView(String templateName, String message) {
        super(templateName);
        this.message = message;
        view = this;
    }

    public String getMessage() {
        if (message == null) {
            return null;
        }
        if (ERROR_MAP.get(message) != null) {
            return ERROR_MAP.get(message);
        } else {
            return "Unable to locate user with given credentials";
        }

    }

    public LoginView getView() {
        return view;
    }
}
