package uk.ac.mmu.webmd.bottomup_version_10;

/**
 * Created by Tom on 26/08/2016.
 */
public class ActivityLog {

    private int activityID;
    private String accountName;
    private String date;
    private String timeStill;
    private String timeWalking;


    public ActivityLog(String accountName, String date, String timeStill, String timeWalking) {
        this.accountName = accountName;
        this.date = date;
        this.timeStill = timeStill;
        this.timeWalking = timeWalking;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountID) {
        this.accountName = accountID;
    }

    public String getTimeStill() {
        return timeStill;
    }

    public void setTimeStill(String timeStill) {
        this.timeStill = timeStill;
    }

    public String getTimeWalking() {
        return timeWalking;
    }

    public void setTimeWalking(String timeWalking) {
        this.timeWalking = timeWalking;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

}
