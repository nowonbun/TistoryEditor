package Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tsn_oauth_info")
@NamedQuery(name = "OauthInfo.findAll", query = "SELECT t FROM OauthInfo t")
public class OauthInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idx;

	@Column(name = "CLIENT_ID")
	private String clientId;

	@Column(name = "CLIENT_SECRET")
	private String clientSecret;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	@Column(name = "GRANT_TYPE")
	private String grantType;

	private byte isdeleted;

	@Column(name = "REDIRECT_URL")
	private String redirectUrl;

	@Column(name = "RESPONSE_TYPE")
	private String responseType;

	private String state;

	@OneToMany(mappedBy = "oAuthInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AccessToken> accessTokens;

	public OauthInfo() {
	}

	public int getIdx() {
		return this.idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return this.clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Date getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getGrantType() {
		return this.grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public byte getIsdeleted() {
		return this.isdeleted;
	}

	public void setIsdeleted(byte isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getRedirectUrl() {
		return this.redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getResponseType() {
		return this.responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<AccessToken> getAccessTokens() {
		return this.accessTokens;
	}

	public void setTsnAccessTokens(List<AccessToken> accessTokens) {
		this.accessTokens = accessTokens;
	}

	public AccessToken addTsnAccessToken(AccessToken accessToken) {
		getAccessTokens().add(accessToken);
		accessToken.setOauthInfo(this);

		return accessToken;
	}

	public AccessToken removeAccessToken(AccessToken accessToken) {
		getAccessTokens().remove(accessToken);
		accessToken.setOauthInfo(null);

		return accessToken;
	}

}