package Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tsn_access_token")
@NamedQuery(name="AccessToken.findAll", query="SELECT t FROM AccessToken t")
public class AccessToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idx;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	private boolean isdeleted;

	@Column(name="TOKEN_KEY")
	private String tokenKey;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="OAUTH_IDX")
	private OauthInfo oAuthInfo;

	public AccessToken() {
	}

	public int getIdx() {
		return this.idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public Date getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public boolean getIsdeleted() {
		return this.isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getTokenKey() {
		return this.tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	public OauthInfo getOauthInfo() {
		return this.oAuthInfo;
	}

	public void setOauthInfo(OauthInfo oAuthInfo) {
		this.oAuthInfo = oAuthInfo;
	}

}