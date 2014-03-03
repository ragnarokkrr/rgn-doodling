package org.ragna.axon2;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

public class TodoItemTest {

	private FixtureConfiguration<TodoItem> fixture;

	@Before
	public void setup() throws Exception {
		fixture = Fixtures.newGivenWhenThenFixture(TodoItem.class);
	}

	@Test
	public void testCreatetodoItem() {
		fixture.given()
				.when(new CreateToDoItemCommand("todo1",
						"need to implement the aggregate"))
				.expectEvents(
						new TodoItemCreatedEvent("todo1",
								"need to implement the aggregate"));
	}

	@Test
	public void testMarkTodoItemAsCompleted() {
		fixture.given(
				new TodoItemCreatedEvent("todo1",
						"need to implement the aggregate"))
				.when(new MarkCompletedCommand("todo1"))
				.expectEvents(new TodoItemCompletedEvent("todo1"));
	}

}
