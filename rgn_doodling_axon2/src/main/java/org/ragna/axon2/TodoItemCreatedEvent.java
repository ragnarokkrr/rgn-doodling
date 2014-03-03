package org.ragna.axon2;

public class TodoItemCreatedEvent {

	private final String todoId;
	private final String description;

	public TodoItemCreatedEvent(String todoId, String description) {
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
		return "TodoItemCreatedEvent [todoId=" + todoId
				+ ", description=" + description + "]";
	}

}
