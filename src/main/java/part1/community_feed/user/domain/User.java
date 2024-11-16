package part1.community_feed.user.domain;

import java.util.Objects;
import part1.community_feed.common.domain.PositiveIntegerCounter;

public class User {
    // 1. 유저의 정보를 입력 받음
    // 2. 유저의 유효성 정보를 확인 (이름이 빈 값인지)
    // 3. 유저 정보 저장

    // ---

    // 1. 팔로우 유저, 팔로우 당하는 유저 입력을 받고 불러오기 : Controller, Service
    // 2. 팔로우 여부 확인하기 : Repository
    //  - 팔로우 상태라면, 에러 반환하기 : Service
    // 3. 자기 자신이라면 에러를 반환하기 : Domain
    // 4. 팔로우 유저 카운트 변경 : Domain
    // 5. 팔로잉 유저 카운트 변경 : Domain
    // 6. 팔로우 여부 저장 및 유저 변경 사항 저장 // Repository

    private final Long id;
//    private final String name;
//    private final String profileImageUrl;
    private final UserInfo info;
    private final PositiveIntegerCounter followingCount;
    private final PositiveIntegerCounter followerCount;

    public User(Long id, UserInfo userInfo) {
        this.id = id;
//        this.name = name;
//        this.profileImageUrl = profileImageUrl;
        this.info = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCount = new PositiveIntegerCounter();
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

    public Long getId() {
        return id;
    }
}
