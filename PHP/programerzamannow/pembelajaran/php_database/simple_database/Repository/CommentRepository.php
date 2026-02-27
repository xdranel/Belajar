<?php

namespace Repository {

    use Model\Comment;

    interface commentRepository
    {
        public function insert(Comment $comment): Comment;

        public function findById(int $id): ?Comment;

        public function findAll(): array;
    }

    class commentRepositoryImpl implements commentRepository
    {
        public function __construct(private \PDO $connection)
        {
        }

        public function insert(Comment $comment): Comment
        {
            $sql = "INSERT INTO comments (email, comment) VALUES (:email , :comment)";
            $stmt = $this->connection->prepare($sql);

            $emailBind = $comment->getEmail();
            $commentBind = $comment->getComment();

            $stmt->bindParam(':email', $emailBind);
            $stmt->bindParam(':comment', $commentBind);
            $stmt->execute();

            $id = $this->connection->lastInsertId();
            $comment->setId($id);
            return $comment;
        }

        public function findById(int $id): ?Comment
        {
            $sql = "SELECT * FROM comments WHERE id = :id";
            $stmt = $this->connection->prepare($sql);

            $stmt->execute([':id' => $id]);

            if ($row = $stmt->fetch()) {
                return new Comment(
                    id: $row["id"],
                    email: $row["email"],
                    comment: $row["comment"]
                );
            } else {
                return null;
            }
        }

        public function findAll(): array
        {
            $sql = "SELECT * FROM comments";
            $stmt = $this->connection->query($sql);

            $array = [];

            while ($row = $stmt->fetch()) {
                $array[] = new Comment(
                    id: $row["id"],
                    email: $row["email"],
                    comment: $row["comment"]
                );
            }

            return $array;
        }
    }
}

