package Events;

import Pojo.Activity;
import Pojo.Statement;

public class LoginEvent
{
    private  String  message;

    Activity activity;

    public void setMessage(String message) {
        this.message = message;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getMessage() {
        return message;
    }


}
