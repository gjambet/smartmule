package guillaume.smartmule;

import java.util.Date;

public final class Activity {

    private static Activity activity;

    private Activity() {
    }

    public static Activity get() {

        if (activity == null) {
            activity = new Activity();
        }

        return activity;
    }

    public String started() {
        return new Date().toGMTString();
    }
}
