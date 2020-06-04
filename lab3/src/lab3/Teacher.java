package lab3;

public class Teacher extends Thread{
    final protected int maxStudents = 10;
    final protected int labsPerTick = 5;
    final String prefix;

    public Teacher(Subject subject, RobotQueue queue, String name){
        super();
        this.subject = subject;
        this.queue = queue;
        this.setName(name);
        this.prefix = String.format("[ROBO] %s | %s: ", subject, name);
        System.out.println(prefix + "is ready.");
    }

    private Subject subject;
    private boolean closed = false;
    private RobotQueue queue;

    @Override
    public void run() {
        try {
            System.out.println(prefix + "Reception of " + subject + " has begun.");
            while (!closed) {
                Student student = null;
                try {
                    while ((student == null) && !closed) {
                        student = queue.poll(subject);
                    }
                    if (closed) {
                        break;
                    }

                    int labsToDo = student.getLabs();
                    while (labsToDo > 0) {
                        labsToDo -= labsPerTick;
                        sleep(10);
                        System.out.println(prefix + "is is work... Remaining: " + labsToDo);
                    }
                    System.out.println(prefix + "checked " + student.getLabs() + " labs.");
                } catch (NullPointerException e) {
                    System.out.println(prefix + "Something wrong has happened");
                }
            }
            System.out.println(prefix + "job's done.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setClosed(boolean closed)
    {
        this.closed = closed;
    }
}
