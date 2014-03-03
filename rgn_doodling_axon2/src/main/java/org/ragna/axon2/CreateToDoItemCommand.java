package org.ragna.axon2;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class CreateToDoItemCommand {

	@TargetAggregateIdentifier
	private final String todoId;
	private final String description;

	public CreateToDoItemCommand(String todoId, String description) {
		super();
		this.todoId = todoId;
		this.description = description;
	}

	public String getTodoId() {
		return todoId;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "CreateToDoItemCommand [todoId=" + todoId + ", description="
				+ description + "]";
	}

}
