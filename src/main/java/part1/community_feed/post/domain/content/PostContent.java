package part1.community_feed.post.domain.content;

public class PostContent extends Content {

    private static final int MIN_POST_LENGTH = 5;
    private static final int MAX_POST_LENGTH = 500;

    public PostContent(String content) {
        super(content);

    }

    @Override
    protected void checkText(String contentText) {
        if (contentText == null || contentText.isEmpty()) {
            throw new IllegalArgumentException("내용이 비어있습니다.");
        }

        if (contentText.length() < MIN_POST_LENGTH || contentText.length() > MAX_POST_LENGTH) {
            throw new IllegalArgumentException("게시글은 5자 이상 500자 이하로 작성해주세요.");
        }

    }
}

