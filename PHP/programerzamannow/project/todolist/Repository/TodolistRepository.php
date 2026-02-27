<?php

namespace Repository {

    use Entity\Todolist;

    interface TodolistRepository
    {

        function save(Todolist $todolist): void;

        function remove(int $number): bool;

        function findAll(): array;
    }

    class TodolistRepositoryImpl implements TodolistRepository
    {
        private array $todolist = array();

        private \PDO $connection;

        public function __construct(\PDO $connection)
        {
            $this->connection = $connection;
        }

        function save(Todolist $todolist): void
        {
            // $number = sizeof($this->todolist) + 1;
            // $this->todolist[$number] = $todolist;

            $sql = "INSERT INTO todolist(todo) VALUES (:todo)";
            $stmt = $this->connection->prepare($sql);

            $todoBind = $todolist->getTodo();
            $stmt->bindParam(':todo', $todoBind);
            $stmt->execute();
        }

        function remove(int $number): bool
        {
//            if ($number > sizeof($this->todolist)) {
//                return false;
//            }
//
//            for ($i = $number; $i < sizeof($this->todolist); $i++) {
//                $this->todolist[$i] = $this->todolist[$i + 1];
//            }
//
//            unset($this->todolist[sizeof($this->todolist)]);
//
//            return true;

            $sql = "SELECT id FROM todolist WHERE id = :id";
            $stmt = $this->connection->prepare($sql);
            $stmt->bindParam(':id', $number);
            $stmt->execute();

            if ($stmt->fetch()) {
                // todolist ada
                $sql = "DELETE FROM todolist WHERE id = :id";
                $stmt = $this->connection->prepare($sql);
                $stmt->bindParam(':id', $number);
                $stmt->execute();
                return true;
            } else {
                // todolist tidak ada
                return false;
            }

        }

        function findAll(): array
        {
            //return $this->todolist;

            $sql = "SELECT id, todo FROM todolist";
            $stmt = $this->connection->prepare($sql);
            $stmt->execute();

            $result = [];

            foreach ($stmt as $row) {
                $todolist = new Todolist();
                $todolist->setId($row['id']);
                $todolist->setTodo($row['todo']);

                $result[] = $todolist;
            }

            return $result;
        }
    }
}
