let todoList = [];
let storedTodoList = localStorage.getItem('todoListPage');
if (storedTodoList) {
  todoList = JSON.parse(storedTodoList);
} else {
  todoList = [];
}
renderTodoList();

function renderTodoList() {
  let todoListHTML = '';

  for (let i = 0; i < todoList.length; i++) {
    //const todoObject = todoList[i];
    //const name = todoObject.name;
    //const dueDate = todoObject.dueDate;
    //const { name, dueDate } = todoObject;
    const { name, dueDate } = todoList[i];
    const html = `
        <div>${name}</div>
        <div>${dueDate}</div>
        <button onclick="
        deleteTodo(${i})"
        class="designButton deleteTodoButton"
        >Delete</button> 
      `;
    todoListHTML += html;
  }
  document.querySelector('.js-todoList').innerHTML = todoListHTML;
}


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
