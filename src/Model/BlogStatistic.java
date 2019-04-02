package Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tsn_blog_statistics")
@NamedQuery(name = "BlogStatistic.findAll", query = "SELECT t FROM BlogStatistic t")
public class BlogStatistic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idx;

	@Column(name = "`COMMENT`")
	private int comment;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastupdateddate;

	private int guestbook;

	private int invitation;

	private boolean isdeleted;

	private int post;

	private int trackback;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BLOG_IDX")
	private Blog blog;

	public BlogStatistic() {
	}

	public int getIdx() {
		return this.idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getComment() {
		return this.comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public Date getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public int getGuestbook() {
		return this.guestbook;
	}

	public void setGuestbook(int guestbook) {
		this.guestbook = guestbook;
	}

	public int getInvitation() {
		return this.invitation;
	}

	public void setInvitation(int invitation) {
		this.invitation = invitation;
	}

	public boolean getIsdeleted() {
		return this.isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public int getPost() {
		return this.post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public int getTrackback() {
		return this.trackback;
	}

	public void setTrackback(int trackback) {
		this.trackback = trackback;
	}

	public Blog getBlog() {
		return this.blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Date getLastupdateddate() {
		return this.lastupdateddate;
	}

	public void setLastupdateddate(Date lastupdateddate) {
		this.lastupdateddate = lastupdateddate;
	}

}