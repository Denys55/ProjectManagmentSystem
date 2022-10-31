package com.managersystem.command;

/**
 * Pattern Command, use command from output for find action in business logic
 *
 */
public interface Command {

    /**
     * execute action
     *
     * @return data to console
     */
    public String execute();
}
