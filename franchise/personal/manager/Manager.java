package franchise.personal.manager;

import franchise.Restaurant;
import franchise.personal.Personal;

public abstract class Manager extends Personal implements IManager {

    public Manager(String name, IPersonalKind kind, double salary, double percent) {
        super(name, PersonalType.MANAGER, kind, salary, percent);
    }

}
