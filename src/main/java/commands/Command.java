package commands;

public abstract class Command {
    public abstract void execute();

    public boolean isBye() {
        return false;
    }
}
