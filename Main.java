import java.util.*;

interface Display {
    void show();
}

abstract class Student implements Display {
    private String name;
    private String id;
    protected ArrayList<Double> marks = new ArrayList<>();

    Student(String n, String i) {
        name = n;
        id = i;
    }

    public String getId() {
        return id;
    }

    public void addMark(double m) {
        if (m < 0 || m > 100) throw new IllegalArgumentException();
        marks.add(m);
    }

    public void modifyMark(int index, double newMark) {
        if (index < 0 || index >= marks.size()) throw new IndexOutOfBoundsException();
        marks.set(index, newMark);
    }

    public double avg() {
        if (marks.size() == 0) return 0;
        double s = 0;
        for (double m : marks) s += m;
        return s / marks.size();
    }

    public abstract boolean isPass();

    public void show() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Marks: " + marks);
        System.out.println("Average: " + avg());
        System.out.println(isPass() ? "PASS" : "FAIL");
    }
}

class Undergraduate extends Student {
    Undergraduate(String n, String i) {
        super(n, i);
    }

    public boolean isPass() {
        return avg() >= 60;
    }
}

class Graduate extends Student {
    Graduate(String n, String i) {
        super(n, i);
    }

    public boolean isPass() {
        return avg() >= 80;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> list = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1 Add Student");
            System.out.println("2 Add Marks");
            System.out.println("3 Modify Mark");
            System.out.println("4 Show All");
            System.out.println("5 Exit");

            int c = sc.nextInt();
            sc.nextLine();

            if (c == 1) addStudent();
            else if (c == 2) addMarks();
            else if (c == 3) modify();
            else if (c == 4) showAll();
            else if (c == 5) break;
        }
    }

    static void addStudent() {
        System.out.print("Name: ");
        String n = sc.nextLine();
        System.out.print("ID: ");
        String i = sc.nextLine();

        System.out.print("1 Undergraduate / 2 Graduate: ");
        int t = sc.nextInt();
        sc.nextLine();

        if (t == 1) list.add(new Undergraduate(n, i));
        else list.add(new Graduate(n, i));
    }

    static Student find(String id) {
        for (Student s : list) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    static void addMarks() {
        System.out.print("ID: ");
        String id = sc.nextLine();
        Student s = find(id);

        if (s == null) return;

        System.out.print("How many: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            double m = sc.nextDouble();
            try {
                s.addMark(m);
            } catch (Exception e) {
                i--;
            }
        }
        sc.nextLine();
    }

    static void modify() {
        System.out.print("ID: ");
        String id = sc.nextLine();
        Student s = find(id);

        if (s == null) return;

        System.out.print("Index: ");
        int index = sc.nextInt();
        System.out.print("New Mark: ");
        double m = sc.nextDouble();

        try {
            s.modifyMark(index, m);
        } catch (Exception e) {
            System.out.println("Error");
        }
        sc.nextLine();
    }

    static void showAll() {
        for (Student s : list) {
            s.show();
            System.out.println("-----");
        }
    }
}