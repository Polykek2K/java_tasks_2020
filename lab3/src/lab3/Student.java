package lab3;

public class Student implements Comparable<Student>{
    public Student(int labs, Subject subject){
        this.labs = labs;
        this.subject = subject;
    }

    private final int labs;
    private final Subject subject;

    public int getLabs() {
        return this.labs;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public static Student getRandomStudent(){
        int labs;
        Subject subject;
        int s = (int)(Math.random() * 3);
        switch (s){
            case 0:
                subject = Subject.MATH;
                break;

            case 1:
                subject = Subject.PHYS;
                break;

            default:
                subject = Subject.PROG;
        }
        s = (int)(Math.random() * 3);
        switch (s) {
            case 0:
                labs = 10;
                break;

            case 1:
                labs = 20;
                break;

            default:
                labs = 100;
        }
        return new Student(labs, subject);
    }

    @Override
    public int compareTo(Student student) {
        return Integer.compare(this.labs, student.labs);
    }
}
