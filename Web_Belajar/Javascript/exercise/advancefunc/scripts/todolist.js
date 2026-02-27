let todoList = [];
let storedTodoList = localStorage.getItem('todoListPage');
if (storedTodoList) {
  todoList = JSON.parse(storedTodoList);
} else {
  todoList = [];
}
renderTodoList();

// rendering
function renderTodoList() {
  let todoListHTML = '';

  todoList.forEach((todoObject, index) => {
    const { name, dueDate } = todoObject;
    const html = `
        <div>${name}</div>
        <div>${dueDate}</div>
        <button 
        class="js-deleteTodoButton deleteTodoButton designButton"
        >Delete</button> 
      `;
    todoListHTML += html;
  });
  document.querySelector('.js-todoList').innerHTML = todoListHTML;

  // event listeners section
  document.querySelectorAll('.js-deleteTodoButton').forEach((deleteButton, index) => {
    deleteButton.addEventListener('click', () => {
      deleteTodo(index)
    });
  });
}

// event listeners section
document.querySelector('.js-addTodoButton').addEventListener('click', () => {
  addTodo();
});

// main function
function addTodo() {
  const inputElement = document.querySelector('.js-todoInput');
  const name = inputElement.value;

  const inputDateElement = document.querySelector('.js-dueDateInput');
  const dueDate = inputDateElement.value;

  if (name && dueDate) {
    todoList.push({
      name,
      dueDate
    });
    inputElement.value = '';
    inputDateElement.value = '';

    renderTodoList();
    localStorage.setItem('todoListPage', JSON.stringify(todoList));
  }
}

function deleteTodo(index) {
  todoList.splice(index, 1);
  localStorage.setItem('todoListPage', JSON.stringify(todoList));
  renderTodoList();
}

