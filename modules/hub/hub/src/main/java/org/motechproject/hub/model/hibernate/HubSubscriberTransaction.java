package org.motechproject.hub.model.hibernate;

// Generated Apr 21, 2014 6:59:44 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * HubSubscriberTransaction generated by hbm2java
 */
@Entity
@Table(name = "hub_subscriber_transaction", schema = "hub")
public class HubSubscriberTransaction implements java.io.Serializable {

	private static final long serialVersionUID = -2823908898058704053L;
	
	private long subscriberTransactionId;
	private HubDistributionStatus hubDistributionStatus;
	private HubSubscription hubSubscription;
	private int retryCount;
	private Date createTime;
	private Date lastUpdated;
	private String createdBy;
	private String lastUpdatedBy;
	private String content;
	private String contentType;

	public HubSubscriberTransaction() {
	}

	public HubSubscriberTransaction(long subscriberTransactionId,
			HubDistributionStatus hubDistributionStatus,
			HubSubscription hubSubscription, int retryCount, String contentType) {
		this.subscriberTransactionId = subscriberTransactionId;
		this.hubDistributionStatus = hubDistributionStatus;
		this.hubSubscription = hubSubscription;
		this.retryCount = retryCount;
		this.contentType = contentType;
	}

	public HubSubscriberTransaction(long subscriberTransactionId,
			HubDistributionStatus hubDistributionStatus,
			HubSubscription hubSubscription, int retryCount, Date createTime,
			Date lastUpdated, String createdBy, String lastUpdatedBy,
			String content, String contentType) {
		this.subscriberTransactionId = subscriberTransactionId;
		this.hubDistributionStatus = hubDistributionStatus;
		this.hubSubscription = hubSubscription;
		this.retryCount = retryCount;
		this.createTime = createTime;
		this.lastUpdated = lastUpdated;
		this.createdBy = createdBy;
		this.lastUpdatedBy = lastUpdatedBy;
		this.content = content;
		this.contentType = contentType;
	}

	@Id
	@Column(name = "subscriber_transaction_id", unique = true, nullable = false)
	public long getSubscriberTransactionId() {
		return this.subscriberTransactionId;
	}

	public void setSubscriberTransactionId(long subscriberTransactionId) {
		this.subscriberTransactionId = subscriberTransactionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "distribution_status_id", nullable = false)
	public HubDistributionStatus getHubDistributionStatus() {
		return this.hubDistributionStatus;
	}

	public void setHubDistributionStatus(
			HubDistributionStatus hubDistributionStatus) {
		this.hubDistributionStatus = hubDistributionStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscription_id", nullable = false)
	public HubSubscription getHubSubscription() {
		return this.hubSubscription;
	}

	public void setHubSubscription(HubSubscription hubSubscription) {
		this.hubSubscription = hubSubscription;
	}

	@Column(name = "retry_count", nullable = false)
	public int getRetryCount() {
		return this.retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "create_time", length = 15)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "last_updated", length = 15)
	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Column(name = "created_by", length = 15)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "last_updated_by", length = 15)
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "content_type", nullable = false, length = 25)
	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}