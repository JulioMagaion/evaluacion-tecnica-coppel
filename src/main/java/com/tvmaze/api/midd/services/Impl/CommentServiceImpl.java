package com.tvmaze.api.midd.services.Impl;



import com.tvmaze.api.midd.dtos.CommentRequestDto;
import com.tvmaze.api.midd.models.CommentDocument;
import com.tvmaze.api.midd.repositories.CommentRepository;
import com.tvmaze.api.midd.services.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final CommentRepository commentRepository;

    //Inyeccíon por constructor
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void saveComment(CommentRequestDto request) {
        log.info("Guardando comentario para el show ID: {}", request.show_id());

        CommentDocument document = new CommentDocument(
                request.show_id(),
                request.comment(),
                request.rating()
        );

        commentRepository.save(document);
        log.info("Comentario guardado exitosamente");
    }
}