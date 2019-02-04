package Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tsn_category")
@NamedQuery(name = "Category.findAll", query = "SELECT t FROM Category t")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idx;

	@Column(name = "CATEGORY_ID")
	private String categoryId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	private String entries;

	private byte isdeleted;

	@Column(name = "`LABEL`")
	private String label;

	private String name;

	private String parent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BLOG_IDX")
	private Blog blog;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts;

	public Category() {
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

	public String getEntries() {
		return this.entries;
	}

	public void setEntries(String entries) {
		this.entries = entries;
	}

	public byte getIsdeleted() {
		return this.isdeleted;
	}

	public void setIsdeleted(byte isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Blog getBlog() {
		return this.blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setCategory(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setCategory(null);

		return post;
	}

}