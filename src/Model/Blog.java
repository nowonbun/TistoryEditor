package Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tsn_blog")
@NamedQuery(name = "Blog.findAll", query = "SELECT t FROM Blog t")
public class Blog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idx;

	@Column(name = "BLOG_ICON_URL")
	private String blogIconUrl;

	private String blogid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	@Column(name = "`DEFAULT`")
	private String default_;

	private String description;

	@Column(name = "FAVICON_URL")
	private String faviconUrl;

	private byte isdeleted;

	private String name;

	private String nickname;

	@Column(name = "PROFILE_IMAGE_URL")
	private String profileImageUrl;

	@Column(name = "PROFILE_THUMBNAIL_IMAGE_URL")
	private String profileThumbnailImageUrl;

	private String role;

	@Column(name = "SECONDARY_URL")
	private String secondaryUrl;

	private String title;

	private String url;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_IDX")
	private TistoryUser tistoryUser;

	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<BlogStatistic> blogStatistics;

	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Category> categories;

	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts;

	public Blog() {
	}

	public int getIdx() {
		return this.idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getBlogIconUrl() {
		return this.blogIconUrl;
	}

	public void setBlogIconUrl(String blogIconUrl) {
		this.blogIconUrl = blogIconUrl;
	}

	public String getBlogid() {
		return this.blogid;
	}

	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}

	public Date getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getDefault_() {
		return this.default_;
	}

	public void setDefault_(String default_) {
		this.default_ = default_;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFaviconUrl() {
		return this.faviconUrl;
	}

	public void setFaviconUrl(String faviconUrl) {
		this.faviconUrl = faviconUrl;
	}

	public byte getIsdeleted() {
		return this.isdeleted;
	}

	public void setIsdeleted(byte isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfileImageUrl() {
		return this.profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getProfileThumbnailImageUrl() {
		return this.profileThumbnailImageUrl;
	}

	public void setProfileThumbnailImageUrl(String profileThumbnailImageUrl) {
		this.profileThumbnailImageUrl = profileThumbnailImageUrl;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSecondaryUrl() {
		return this.secondaryUrl;
	}

	public void setSecondaryUrl(String secondaryUrl) {
		this.secondaryUrl = secondaryUrl;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TistoryUser getTistoryUser() {
		return this.tistoryUser;
	}

	public void setTistoryUser(TistoryUser tistoryUser) {
		this.tistoryUser = tistoryUser;
	}

	public List<BlogStatistic> getBlogStatistics() {
		return this.blogStatistics;
	}

	public void setBlogStatistics(List<BlogStatistic> blogStatistics) {
		this.blogStatistics = blogStatistics;
	}

	public BlogStatistic addBlogStatistic(BlogStatistic blogStatistic) {
		getBlogStatistics().add(blogStatistic);
		blogStatistic.setBlog(this);

		return blogStatistic;
	}

	public BlogStatistic removeblogStatistic(BlogStatistic blogStatistic) {
		getBlogStatistics().remove(blogStatistic);
		blogStatistic.setBlog(null);

		return blogStatistic;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category addTsnCategory(Category category) {
		getCategories().add(category);
		category.setBlog(this);

		return category;
	}

	public Category removeTsnCategory(Category category) {
		getCategories().remove(category);
		category.setBlog(null);

		return category;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setBlog(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setBlog(null);

		return post;
	}

}