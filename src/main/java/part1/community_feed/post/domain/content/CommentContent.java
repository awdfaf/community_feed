package part1.community_feed.post.domain.content;

public class CommentContent extends Content {

    private static final int MAX_COMMENT_LENGTH = 100;

    public CommentContent(String contentText) {
        super(contentText);
    }

    @Override
    protected void checkText(String contenetText) {
        if (contenetText == null || contenetText.isEmpty()) {
            throw new IllegalArgumentException("내용이 비어있습니다.");
        }

        if (contenetText.length() > MAX_COMMENT_LENGTH) {
            throw new IllegalArgumentException("댓글은 100자 이하로 작성해주세요.");
        }
    }
}
