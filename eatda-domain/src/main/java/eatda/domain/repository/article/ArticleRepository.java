package eatda.domain.repository.article;

import eatda.domain.domain.article.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
