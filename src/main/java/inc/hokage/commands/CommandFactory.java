package inc.hokage.commands;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.isNull;

public abstract class CommandFactory {
    private static final Map<String, Command> commandsMap = new HashMap<>();

    static {
        Reflections reflections = new Reflections("inc.hokage.commands");

        Set<Class<? extends Object>> commandClasses =
                reflections.getTypesAnnotatedWith(CommandProvider.class);

        commandClasses.forEach(c-> {
            try {
                commandsMap.put(c.getAnnotation(CommandProvider.class).key(),
                        (Command) c.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public static Command getCommand(String key){
        return isNull(commandsMap.get(key)) ?
                commandsMap.get(InvalidOptionPrinter.class.getAnnotation(CommandProvider.class).key()):
                commandsMap.get(key);
    }
}
