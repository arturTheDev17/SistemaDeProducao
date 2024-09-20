package data;

import java.util.ArrayList;

public class DataEmployee implements Data {
    //TODO personalizar para ter subclasse de chefe e peao

    private ArrayList<String> problems;

    public String typeEmployee;

    public String machineName;


    public DataEmployee(ArrayList<String> problems, String typeEmployee, String machineName) {
        this.problems = problems;
        this.typeEmployee = typeEmployee;
        this.machineName = machineName;
    }

    public DataEmployee() {};

    public String getTypeEmployee() {
        return typeEmployee;
    }

    public void setTypeEmployee(String typeEmployee) {
        this.typeEmployee = typeEmployee;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
    public ArrayList<String> getProblems() {
        return problems;
    }

}
