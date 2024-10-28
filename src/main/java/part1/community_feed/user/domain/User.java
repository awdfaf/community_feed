package part1.community_feed.user.domain;

import java.util.Objects;

public class User {
    private final Long id;
//    private final String name;
//    private final String profileImageUrl;
    private final UserInfo info;
    private final UserRelationCounter followingCount;
    private final UserRelationCounter followerCount;

    public User(Long id, UserInfo userInfo) {
        this.id = id;
//        this.name = name;
//        this.profileImageUrl = profileImageUrl;
        this.info = userInfo;
        this.followingCount = new UserRelationCounter();
        this.followerCount = new UserRelationCounter();
    }

    public void follow(User targetUser) {
        if (this.equals(targetUser)) {
            throw new IllegalArgumentException("자기 자신을 팔로우할 수 없습니다.");
        }
        followingCount.increase();
        // 디미터(Demeter)의 법칙 위반 -> private 메소드로 해결
        //targetUser.followerCount.increase();
        targetUser.increaseFollowerCount();
    }

    public void unfollow(User targetUser) {
        if (this.equals(targetUser)) {
            throw new IllegalArgumentException("자기 자신과의 팔로우를 취소할 수 없습니다.");
        }
        followingCount.decrease();
        //targetUser.followerCount.decrease();
        targetUser.decreaseFollowerCount();
    }

    private void increaseFollowerCount() {
        followerCount.increase();
    }

    private void decreaseFollowerCount() {
        followerCount.decrease();
    }


    // id를 통해 객체를 구분해야함
    // equals와 hashCode를 오버라이드 해야함
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
