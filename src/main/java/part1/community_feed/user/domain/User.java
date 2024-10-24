package part1.community_feed.user.domain;

public class User {
    private final Long id;
    private final String name;
    private final String profileImageUrl;

    public User(Long id, String name, String profileImageUrl) {
        this.id = id;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }
}
