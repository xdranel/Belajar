package gendhiramona.database.Repository;

import gendhiramona.database.Entity.Comment;

import java.util.List;

public interface CommentRepository {

    void insert(Comment comment);

    Comment findById(int id);

    List<Comment> findAll();

    List<Comment> findByEmail(String email);
}
