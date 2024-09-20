package data;

public abstract class DataEmployee implements Data {

    private String nameEmployee;

    private String titleLog;

    private String messageLog;

    public String typeEmployee;


    public DataEmployee(String nameEmployee, String titleLog, String messageLog, String typeEmployee) {
        this.nameEmployee = nameEmployee;
        this.titleLog = titleLog;
        this.messageLog = messageLog;
        this.typeEmployee = typeEmployee;
    }

    public DataEmployee() {};

    public String getNameEmployee() {
        return nameEmployee;
    }

    public String getTitleLog() {
        return titleLog;
    }

    public String getMessageLog() {
        return messageLog;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public void setTitleLog(String titleLog) {
        this.titleLog = titleLog;
    }

    public void setMessageLog(String messageLog) {
        this.messageLog = messageLog;
    }
}
