package com.comm;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class Page<T> {
    private final List<T> content = new ArrayList<T>();
    private final Long totalCount;
    private final Pageable pageable;
    /** 查询结果. */
    private Object result;
    
    private Long pageNo = 1L;
    /** 正序. */
    public static final String ASC = "ASC";
    /** 倒序. */
    public static final String DESC = "DESC";
    /** 总页数，默认值为-1，表示pageCount不可用. */
    private Long pageCount = -1L;

    
    public Object getResult() {
		return content;
	}
	public boolean isAsc() {
        if(pageable.getOrderDirection()==Order.Direction.asc){
        		return true;
        }else{
        		return false;
        }
        	
    }
    /** @return orderBy. */
    public String getOrderBy() {
        if (!pageable.getOrders().isEmpty()) {
            return pageable.getOrders().get(0).getProperty();
        }

        return null;
    }
    public void setOrderBy(String orderBy) {
        if ((orderBy == null) || (orderBy.trim().length() == 0)) {
            throw new IllegalArgumentException("orderBy should be blank");
        }
        this.pageable.getOrders().clear();
        Order order=new Order();
        order.setProperty(orderBy);
        this.pageable.getOrders().add(order);
        if (this.getOrders().size() != 1) {
        		pageable.setOrderDirection(Order.Direction.asc);
        }
    }
    public Long getPageCount() {
		return pageable.getPageNumber();
	}
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
		pageable.setPageNumber(pageCount);
	}
	public int getPageNo() {
		return pageable.getPageNumber().intValue();
	}
	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
		pageable.setPageNumber(pageNo);
	}
	public Page() {
        this.totalCount = 0L;
        this.pageable = new Pageable();
    }
    public int getResultSize() {
        if (content instanceof Collection) {
            return ((Collection) content).size();
        } else {
            return 0;
        }
    }
    public Page(List<T> content, long total, Pageable pageable) {
        this.content.addAll(content);
        this.totalCount = total;
        this.pageable = pageable;
    }

    public Long getPageNumber() {
        return this.pageable.getPageNumber();
    }
    public Long getPageSize() {
        return this.pageable.getPageSize();
    }

    public String getSearchProperty() {
        return this.pageable.getSearchProperty();
    }

    public String getSearchValue() {
        return this.pageable.getSearchValue();
    }

    public String getOrderProperty() {
        return this.pageable.getOrderProperty();
    }

    public Order.Direction getOrderDirection() {
        return this.pageable.getOrderDirection();
    }

    public List<Order> getOrders() {
        return this.pageable.getOrders();
    }

    public List<Filter> getFilters() {
        return this.pageable.getFilters();
    }

    public int getTotalPages() {
        return getPageSize() == 0 ? 0 : (int) Math.ceil((double) getTotalCount() / (double) getPageSize());
    }

    public List<T> getContent() {
        return this.content;
    }

    public void setContent(List content) {
        this.content.clear();
        this.content.addAll(content);
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public Pageable getPageable() {
        return this.pageable;
    }

}
