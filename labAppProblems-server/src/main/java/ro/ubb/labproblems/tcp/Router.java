package ro.ubb.labproblems.tcp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Router {


    private Object[] services;

    public Router(Object... services) {

        this.services = services;
    }


    public String route(Request command) {

        for (Object service : services) {
            if (service.getClass().getSimpleName().toLowerCase().startsWith(command.getService().toLowerCase())) {
                return routeToService(service, command);
            }
        }
        return "Unknown service " + command.getService();
    }

    private String routeToService(Object service, Request command) {
        for (Method method : service.getClass().getMethods()) {
            if (method.getName().equals(command.getMethod())) {
                try {
                    return (String) method.invoke(service, (Object[]) command.getArguments());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    return "Failed to execute command: " + e.getMessage();
                }
            }
        }
        return "unknown method " + command.getMethod() + " on service " + command.getService();
    }

}