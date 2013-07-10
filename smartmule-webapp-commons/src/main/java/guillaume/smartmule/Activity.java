package guillaume.smartmule;

import java.util.Date;

public final class Activity {

    private static Activity activity;
    private Date started;
    private String configuration;

    private Activity() {
    }

    public static Activity get() {

        if (activity == null) {
            activity = new Activity();
        }

        return activity;
    }

    public String started() {
        return started.toGMTString();
    }

    public String getConfiguration() {
        return configuration;
    }

    public Activity starting(Date time) {
        started = time;
        return this;
    }

    public Activity withConfiguration(String path) {
        configuration = path;
        return this;
    }
}
