package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, User> mapCurrent = current.stream().collect(Collectors.toMap(User::getId, u -> u));
        Info info = new Info(0, 0, 0);
        User currentValue;
        for (User user : previous) {
            currentValue = mapCurrent.get(user.getId());
            if (currentValue == null) {
                info.setDeleted(info.getDeleted() + 1);
            } else if (!currentValue.equals(user)) {
                info.setChanged(info.getChanged() + 1);
            }
            mapCurrent.remove(user.getId());
        }
        info.setAdded(mapCurrent.size());
        return info;
    }
}