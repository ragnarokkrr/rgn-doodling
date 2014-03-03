package org.ragna.axon2;

public class TodoItemCompletedEvent {

	private final String todoId;

	public TodoItemCompletedEvent(String todoId) {
		super();
		this.todoId = todoId;
	}

	public String getTodoId() {
		return todoId;
	}

	@Override
	public String toString() {
		return "TodoItemCompletedEvent [todoId=" + todoId + "]";
	}

}
