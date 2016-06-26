package com.mossle.book.persistence.domain;

// Generated by Hibernate Tools
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
 * BookBorrow .
 * 
 * @author Lingo
 */
@Entity
@Table(name = "BOOK_BORROW")
public class BookBorrow implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    /** null. */
    private Long id;

    /** null. */
    private BookInfo bookInfo;

    /** null. */
    private String userId;

    /** null. */
    private Date borrowTime;

    /** null. */
    private String borrowOperator;

    /** null. */
    private Date returnTime;

    /** null. */
    private String returnOperator;

    /** null. */
    private Date expireDate;

    /** null. */
    private String renew;

    /** null. */
    private String status;

    /** null. */
    private String description;

    public BookBorrow() {
    }

    public BookBorrow(Long id) {
        this.id = id;
    }

    public BookBorrow(Long id, BookInfo bookInfo, String userId,
            Date borrowTime, String borrowOperator, Date returnTime,
            String returnOperator, Date expireDate, String renew,
            String status, String description) {
        this.id = id;
        this.bookInfo = bookInfo;
        this.userId = userId;
        this.borrowTime = borrowTime;
        this.borrowOperator = borrowOperator;
        this.returnTime = returnTime;
        this.returnOperator = returnOperator;
        this.expireDate = expireDate;
        this.renew = renew;
        this.status = status;
        this.description = description;
    }

    /** @return null. */
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    /**
     * @param id
     *            null.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return null. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INFO_ID")
    public BookInfo getBookInfo() {
        return this.bookInfo;
    }

    /**
     * @param bookInfo
     *            null.
     */
    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    /** @return null. */
    @Column(name = "USER_ID", length = 50)
    public String getUserId() {
        return this.userId;
    }

    /**
     * @param userId
     *            null.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /** @return null. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BORROW_TIME", length = 26)
    public Date getBorrowTime() {
        return this.borrowTime;
    }

    /**
     * @param borrowTime
     *            null.
     */
    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    /** @return null. */
    @Column(name = "BORROW_OPERATOR", length = 64)
    public String getBorrowOperator() {
        return this.borrowOperator;
    }

    /**
     * @param borrowOperator
     *            null.
     */
    public void setBorrowOperator(String borrowOperator) {
        this.borrowOperator = borrowOperator;
    }

    /** @return null. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RETURN_TIME", length = 26)
    public Date getReturnTime() {
        return this.returnTime;
    }

    /**
     * @param returnTime
     *            null.
     */
    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    /** @return null. */
    @Column(name = "RETURN_OPERATOR", length = 64)
    public String getReturnOperator() {
        return this.returnOperator;
    }

    /**
     * @param returnOperator
     *            null.
     */
    public void setReturnOperator(String returnOperator) {
        this.returnOperator = returnOperator;
    }

    /** @return null. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIRE_DATE", length = 26)
    public Date getExpireDate() {
        return this.expireDate;
    }

    /**
     * @param expireDate
     *            null.
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /** @return null. */
    @Column(name = "RENEW", length = 50)
    public String getRenew() {
        return this.renew;
    }

    /**
     * @param renew
     *            null.
     */
    public void setRenew(String renew) {
        this.renew = renew;
    }

    /** @return null. */
    @Column(name = "STATUS", length = 50)
    public String getStatus() {
        return this.status;
    }

    /**
     * @param status
     *            null.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /** @return null. */
    @Column(name = "DESCRIPTION", length = 200)
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     *            null.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
