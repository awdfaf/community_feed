package part1.community_feed.post.domain;

import part1.community_feed.common.domain.PositiveIntegerCounter;
import part1.community_feed.post.domain.content.PostContent;
import part1.community_feed.post.domain.content.PostPublicationState;
import part1.community_feed.user.domain.User;

public class Post {
    // 1. 게시글의 정보를 입력받는다.
    // 2. 유저 유효성 확인 = 존재하는 유저인지
    // 3. 게시글의 유효성 확인 (5자 이상 500자 이하)
    // 4. db 게시글 저장

    // ---

    // 1. 유저번호와 좋아요를 누를 게시글의 번호를 입력받는다.
    // 2. 게시글과 유저의 좋아요가 존재 여부를 확인 : Repository
    // 3. 유저 본인 게시글인지 확인
    // 4. 게시글의 좋아요 수 증가
    // 5. db 저장 및 결과를 반환


    private final Long id;
    private final User author;
    //private final Long authorId;
    private final PostContent content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;


    public Post(Long id, User author, PostContent content) {
        if (author == null) {
            throw new IllegalArgumentException("작성자 정보가 없습니다.");
        }

        this.id = id;
        this.author = author;
        //this.authorId = authorId;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException("자신의 게시글에는 좋아요를 누를 수 없습니다.");
        }
        likeCount.increase();
    }

    public void unlike() {
        this.likeCount.decrease();
    }

    public void updatePost(User user, String updateContent, PostPublicationState state) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException("게시글을 수정할 권한이 없습니다.");
        }
        this.state = state;
        this.content.updateContent(updateContent);
    }
}
