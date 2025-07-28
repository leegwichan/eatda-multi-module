package eatda.api.document;

public enum Tag {

    AUTH_API("Auth API"),
    MEMBER_API("Member API"),
    STORE_API("Store API"),
    STORY_API("Story API"),
    CHEER_API("Cheer API"),
    ARTICLE_API("Article API"),
    ;

    private final String displayName;

    Tag(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
