package part1.community_feed.post.domain.content;

import part1.community_feed.post.domain.common.DatetimeInfo;

public abstract class Content {
    // 게시물, 댓글 검증을 위한 추상 클래스

    String contentText;
    final DatetimeInfo datetimeInfo;


    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.datetimeInfo = new DatetimeInfo();
    }

    public void updateContent(String updateContent) {
        checkText(updateContent);
        this.contentText = updateContent;
        this.datetimeInfo.updateEditDateTime();
    }

    protected abstract void checkText(String contenetText);

    public String getContentText() {
        return contentText;
    }
}
