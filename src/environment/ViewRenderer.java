package environment;

import entities.Entity;

public interface ViewRenderer {
    void remove(Entity entity);
    void render();
}
