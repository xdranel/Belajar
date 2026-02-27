<?php

namespace View {

    use Service\TodolistService;
    use Helper\InputHelper;

    class TodolistView
    {
        private TodolistService $todolistService;

        public function __construct(TodolistService $todolistService)
        {
            $this->todolistService = $todolistService;
        }

        function showTodolist(): void
        {
            system('clear');
            $running = true;
            while ($running != false) {

                $this->todolistService->showTodolist();

                echo "Menu" . PHP_EOL;
                echo "1. Add Todo List" . PHP_EOL;
                echo "2. Remove Todo List" . PHP_EOL;
                echo "X. Exit" . PHP_EOL;

                $choose = InputHelper::input("Pilih");

                switch ($choose) {
                    case "1":
                        $this->addTodolist();
                        sleep(2);
                        break;
                    case "2":
                        $this->removeTodolist();
                        sleep(2);
                        break;
                    case "X":
                    case "x";
                        $running = false;
                        echo "Terimakasih" . PHP_EOL;
                        sleep(2);
                        system('clear');
                        break;
                    default:
                        echo "Pilihan tidak ada" . PHP_EOL;
                        sleep(2);
                }
            }
        }

        function addTodolist(): void
        {

            echo "Menambah Todo List" . PHP_EOL;

            $todo = InputHelper::input("Todo (x untuk batal)");

            if ($todo == "x") {
                echo "Batal Menambahkan Todo List" . PHP_EOL;
            } else {
                $this->todolistService->addTodolist($todo);
            }
        }

        function removeTodolist(): void
        {
            echo "Menghapus Todo List" . PHP_EOL;

            $pilihan = InputHelper::input("Nomor (x) untuk batal");

            if ($pilihan == "x") {
                echo "Batal Menghapus Todo List" . PHP_EOL;
            } else {
                $this->todolistService->removeTodolist($pilihan);
            }
        }
    }
}
