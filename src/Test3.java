import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;


public class Test3 {

    JList<Student> list;
    DefaultListModel model;

    public Test3() {
        list = new JList();
        model = new DefaultListModel();
        for (int i = 0; i < 10; i++) {
            model.addElement(new Student("Paul" + i, i));
        }
        list.setModel(model);
        list.setCellRenderer(new MyListCellRenderer());

        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(list);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Test3();
            }
        });
    }

    private class MyListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            Student label = (Student) value;
            String name = label.getName();
            int age = label.getAge();
            String labelText = "<html>Name: " + name + "<br/>Age: " + age;
            setText(labelText);

            return this;
        }

    }
}

class Student {
    String name;
    int age;
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() { return name; }
    public int getAge() { return age; }
}