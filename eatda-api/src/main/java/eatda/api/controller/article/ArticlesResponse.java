package eatda.api.controller.article;

import java.util.List;

public record ArticlesResponse(
        List<ArticleResponse> articles
) {
}
