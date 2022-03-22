package behaviour;

public class Log extends Leaf {
    public String message;
    @Override
    public void onStart() {
        System.out.println("OnStart{" + message + "}");
    }

    @Override
    public void onStop() {
        System.out.println("OnStop{" + message + "}");
    }

    @Override
    public State onUpdate() {
        System.out.println("OnUpdate{" + message + "}");
        return State.SUCCESS;
    }
}
