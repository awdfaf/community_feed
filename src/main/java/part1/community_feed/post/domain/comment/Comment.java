package part1.community_feed.post.domain.comment;

import part1.community_feed.common.domain.PositiveIntegerCounter;
import part1.community_feed.post.domain.Post;
import part1.community_feed.post.domain.content.Content;
import part1.community_feed.user.domain.User;

public class Comment {

    // 1. 댓글의 정보를 입력받기
    // 2. 유저의 유효성 확인
    // 3. 게시글 유효성 확인
    // 4. 댓글 유효성 확인 : Domain
    // - 100자 초과면 에러 반환
    // 5. db에 댓글 저장


    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    public Comment(Long id, Post post, User author, Content content) {
        if (author == null) {
            throw new IllegalArgumentException("작성자 정보가 없습니다.");
        }

        if(post == null) {
            throw new IllegalArgumentException("게시글 정보가 없습니다.");
        }

        if (content == null) {
            throw new IllegalArgumentException("내용이 없습니다.");
        }


        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
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

    public void updateComment(User user, String updateContent) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException("댓글을 수정할 권한이 없습니다.");
        }
        this.content.updateContent(updateContent);
    }
}
