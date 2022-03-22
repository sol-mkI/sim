package behaviour;

public class Repeater extends Decorator{
    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate() {
        child.update();
        return State.RUNNING;
    }
}
