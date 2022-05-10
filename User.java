import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class User {
    String login;
    Long id;
    URL url;
    URL avatar_url;
    URL follower_url;
    URL following_url;
    URL organization_url;
    URL repo_url;
    URL starred_url;
    String name;
    String company;
    URL blog;
    String location;
    String email;
    String hireable;
    String bio;
    Long followers;
    Long following;
    String created_at;
    String updated_at;
    List<User>followers_list = new ArrayList<>();
    List<User>followings_list = new ArrayList<>();
    public User(Long id,String login){
        this.id = id;
        this.login = login;
    }
    public User(String login){
        this.login = login;
    }

    public User(String login, URL url){
        this.login = login;
        this.url = url;
    }
    public User(
            String login,
            Long id,
            URL avatar_url,
            URL follower_url,
            URL following_url,
            URL organization_url,
            URL repo_url,
            URL starred_url,
            String name,
            String company,
            URL blog,
            String location,
            String email,
            String hireable,
            String bio,
            Long followers,
            Long following,
            String created_at,
            String updated_at) {
        this.login = login;
        this.id = id;
        this.avatar_url = avatar_url;
        this.follower_url = follower_url;
        this.following_url = following_url;
        this.organization_url = organization_url;
        this.repo_url = repo_url;
        this.starred_url = starred_url;
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.location = location;
        this.email = email;
        this.hireable = hireable;
        this.bio = bio;
        this.followers = followers;
        this.following = following;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public URL getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(URL avatar_url) {
        this.avatar_url = avatar_url;
    }

    public URL getFollower_url() {
        return follower_url;
    }

    public void setFollower_url(URL follower_url) {
        this.follower_url = follower_url;
    }

    public URL getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(URL following_url) {
        this.following_url = following_url;
    }

    public URL getOrganization_url() {
        return organization_url;
    }

    public void setOrganization_url(URL organization_url) {
        this.organization_url = organization_url;
    }

    public URL getRepo_url() {
        return repo_url;
    }

    public void setRepo_url(URL repo_url) {
        this.repo_url = repo_url;
    }

    public URL getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(URL stared_url) {
        this.starred_url = stared_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public URL getBlog() {
        return blog;
    }

    public void setBlog(URL blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHireable() {
        return hireable;
    }

    public void setHireable(String hireable) {
        this.hireable = hireable;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getFollowers() {
        return followers;
    }

    public void setFollowers(Long followers) {
        this.followers = followers;
    }

    public Long getFollowing() {
        return following;
    }

    public void setFollowing(Long following) {
        this.following = following;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
