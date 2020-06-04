package lab3;

public class Process extends Thread{
        public Process(RobotQueue queue)
        {
            this.queue = queue;
            System.out.println("Session is prerared to begin!");
        }

    private boolean closed = false;
    private RobotQueue queue;

    @Override
    public void run() {
        try {
            System.out.println("Session is in run");
            while (!closed) {
                queue.add(Student.getRandomStudent(), true);
            }
            System.out.println("Session is over.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setClosed(boolean closed)
    {
        this.closed = closed;
    }
}
