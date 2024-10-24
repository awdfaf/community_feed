package part1.community_feed.user.domain;

import java.util.Objects;

public class User {
    private final Long id;
//    private final String name;
//    private final String profileImageUrl;
    private final UserInfo info;

    public User(Long id, UserInfo userInfo) {
        this.id = id;
//        this.name = name;
//        this.profileImageUrl = profileImageUrl;
        this.info = userInfo;
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
