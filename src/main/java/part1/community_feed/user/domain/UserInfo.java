package part1.community_feed.user.domain;

public class UserInfo {
    // User를 위한 VO
    // User 객체에 적용

    private final String name;
    private final String profileImageUrl;

    public UserInfo(String name, String profileImageUrl) {
        // 이름이 비었는지 확인
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name must not be empty");
        }

        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }
}
