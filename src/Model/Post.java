package Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tsn_post")
@NamedQuery(name = "Post.findAll", query = "SELECT t FROM Post t")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idx;

	@Column(name = "CATEGORY_ID")
	private String categoryId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastupdateddate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private boolean isdeleted;

	private boolean ismodified;

	@Column(name = "POST_ID")
	private String postId;

	@Column(name = "POST_URL")
	private String postUrl;

	@Column(name = "SECONDARY_URL")
	private String secondaryUrl;

	private String tags;

	private String title;

	private String url;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BLOG_IDX")
	private Blog blog;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_IDX")
	private Category category;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTENTS_IDX")
	private Content content;

	public Post() {
	}

	public int getIdx() {
		return this.idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Date getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getIsdeleted() {
		return this.isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public boolean getIsmodified() {
		return this.ismodified;
	}

	public void setIsmodified(boolean ismodified) {
		this.ismodified = ismodified;
	}

	public String getPostId() {
		return this.postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostUrl() {
		return this.postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

	public String getSecondaryUrl() {
		return this.secondaryUrl;
	}

	public void setSecondaryUrl(String secondaryUrl) {
		this.secondaryUrl = secondaryUrl;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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

	public Blog getBlog() {
		return this.blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getLastupdateddate() {
		return this.lastupdateddate;
	}

	public void setLastupdateddate(Date lastupdateddate) {
		this.lastupdateddate = lastupdateddate;
	}

	public Content getContent() {
		return this.content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

}