package com.comm;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Collection;

import org.hibernate.Query;

import com.comm.Filter.Operator;
import com.mossle.core.hibernate.HibernatePagingDao;

public class Tools {
//
//    public Page findPage(Pageable pageable, String tableName,
//            String strGroupBy) {
//        Query query = createTypedQuery(pageable, tableName, strGroupBy);
//        long total = findCount(pageable, tableName);
//        int totalPages = (int) Math.ceil((double) total
//                / (double) pageable.getPageSize());
//        if (totalPages < pageable.getPageNumber()) {
//            pageable.setPageNumber(totalPages);
//        }
//        query.setFirstResult((pageable.getPageNumber() - 1)
//                * pageable.getPageSize());
//        query.setMaxResults(pageable.getPageSize());
//        return new Page(query.list(), total, pageable);
//    }
    public static long[] getDistanceTime(Date one, Date two) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long time1 = one.getTime();
        long time2 = two.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

        long[] times = { day, hour, min, sec };
        return times;
    }

    public static long getDistanceTimes(Date one, Date two) {
        long[] times = getDistanceTime(one, two);
        return times[0] * 24 + times[1];
    }

    public static long getDistanceMin(Date one, Date two) {
        long[] times = getDistanceTime(one, two);
        return times[0] * 24 * 60 + times[1] * 60 + times[2];
    }

    public String findLastProperty(String strProperty) {
        String[] searchPropertys = null;
        String tempstr = null;
        if (strProperty.contains(".")) {
            searchPropertys = strProperty.split("\\.");
            for (int i = 0; i < searchPropertys.length; i++) {
                tempstr = searchPropertys[i];
            }
        } else {
            return strProperty;
        }
        return tempstr;
    }

    public String isDatetoyyyymmdd(Object value) {
        String dateNowStr = "";
        if (value instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            dateNowStr = sdf.format(value);
        }
        return dateNowStr;
    }

    private Boolean isfilterOperatorIsNullOrisNotNull(Filter filter) {
        if (filter.getOperator() == Operator.isNull
                || filter.getOperator() == Operator.isNotNull) {
            return true;
        }
        return false;
    }

    private String isfilterOperatorIsInAndVauleIsNull(Filter filter) {
        if (filter.getOperator() == Operator.in) {
            if (((Collection) filter.getValue()).contains(null)) {
                ((Collection) filter.getValue()).remove(null);
                return " or " + filter.getProperty() + " is null";
            }
        }
        return "";
    }
    public String findFilerParameter2(String operat, String strProperty,
            Filter filter, Object value, int view) {
        String tempstr = null;
        String strSearch = "";
        tempstr = findLastProperty(strProperty);
        if (view == 0) {
            strSearch = strSearch + operat + strProperty + retOperator(filter);
        } else if (view == 1) {
            strSearch = strSearch + operat
                    + findJoinTableName(filter.getProperty())
                    + findNoFirstName(filter.getProperty())
                    + retOperator(filter);
        }
        if (!isfilterOperatorIsNullOrisNotNull(filter)) {
            strSearch = strSearch + ":" + findParameterName(filter);
        }
        return strSearch;
    }

    public String findFilerParameter1(String operat, String strProperty,
            Filter filter, Object value) {
        return findFilerParameter2(operat, strProperty, filter, value, 0);
    }

    public String findFilerParameter_NoAliases(String operat,
            String strProperty, Filter filter, Object value) {
        return findFilerParameter2(operat, strProperty, filter, value, 1);
    }


    public String findFilerParameter(String strProperty, String strOperator,
            Object Value) {
        String strSearch = "";
        strSearch = strSearch + " and " + strProperty + strOperator;
        strSearch = strSearch + Value;
        return strSearch;
    }

    public String retOperatorJudgment(Filter filter, int view) {
        String strOperator = "";
        if (Operator.eq == filter.getOperator()) {
            if (view == 0) {
                strOperator = "eq";
            } else if (view == 1) {
                strOperator = " = ";
            }
        } else if (Operator.ge == filter.getOperator()) {
            if (view == 0) {
                strOperator = "get";
            } else if (view == 1) {
                strOperator = " >= ";
            }
        } else if (Operator.le == filter.getOperator()) {
            if (view == 0) {
                strOperator = "le";
            } else if (view == 1) {
                strOperator = " <= ";
            }
        } else if (Operator.gt == filter.getOperator()) {
            if (view == 0) {
                strOperator = "get";
            } else if (view == 1) {
                strOperator = " > ";
            }
        } else if (Operator.lt == filter.getOperator()) {
            if (view == 0) {
                strOperator = "lt";
            } else if (view == 1) {
                strOperator = " < ";
            }
        } else if (Operator.like == filter.getOperator()) {
            if (view == 0) {
                strOperator = "like";
            } else if (view == 1) {
                strOperator = " like ";
            }
        } else if (Operator.in == filter.getOperator()) {
            if (view == 0) {
                strOperator = "in";
            } else if (view == 1) {
                strOperator = " in ";
            }
        } else if (Operator.isNull == filter.getOperator()) {
            if (view == 0) {
                strOperator = "isNull";
            } else if (view == 1) {
                strOperator = "  is null ";
            }
        } else if (Operator.ne == filter.getOperator()) {
            if (view == 0) {
                strOperator = "ne";
            } else if (view == 1) {
                strOperator = "  != ";
            }
        } else if (Operator.or == filter.getOperator()) {
            if (view == 0) {
                strOperator = "or";
            } else if (view == 1) {
                strOperator = "  or ";
            }
        } else if (Operator.notin == filter.getOperator()) {
            if (view == 0) {
                strOperator = "notin";
            } else if (view == 1) {
                strOperator = "  not in ";
            }
        }
        return strOperator;
    }

    public String retOperatorToString(Filter filter) {
        return retOperatorJudgment(filter, 0);
    }

    public String retOperator(Filter filter) {
        return retOperatorJudgment(filter, 1);
    }
    // 首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder())
                    .append(Character.toUpperCase(s.charAt(0)))
                    .append(s.substring(1)).toString();
    }

    private String findTableNameNoUpperCase(String str) {
        if (str != null && str.contains(".")) {
            String[] tmp = str.split("\\.");
            return tmp[0];
        } else {
            return str;
        }
    }

    private String findTableName(String str) {
        String tempstr = findTableNameNoUpperCase(str);
        return toUpperCaseFirstOne(tempstr);
    }

    private String findNoFirstName(String str) {
        String tempstr = findTableNameNoUpperCase(str);
        return str = str.replace(tempstr, "");
    }

    private Boolean isCollectionProperty(String tableName, Filter filter) {
        Class onwClass;
        try {
            onwClass = Class.forName("com.pankebao.entity." + tableName);
            Object obj = onwClass.newInstance();
            String strDeclaredMethod = "";
            String strfilterProperty = filter.getProperty();
            Method m1 = onwClass.getDeclaredMethod("get"
                    + findTableName(strfilterProperty));
            if (m1 != null) {
                Object objProperty = m1.invoke(obj);
                if (objProperty instanceof Collection) {
                    return true;
                }
            }
        } catch (Exception e) {
        }

        return false;
    }

    private String findJoinTableName(String strName) {
        return findTableName(strName) + "join";
    }

    public String createJoinStr(String tableName, String tableNameLowerCase,
            List<Filter> filters) {
        String joinstr = "";
        for (Filter filter : filters) {
            if (isCollectionProperty(tableName, filter))
                joinstr = "   join " + tableNameLowerCase + "."
                        + findTableNameNoUpperCase(filter.getProperty()) + " "
                        + findJoinTableName(filter.getProperty());
            return joinstr;
        }
        return "";
    }
    private String filterCreate(List<Filter> filters, String aliases,
            String operat) {
        String strSearch = "";
        for (Filter filter : filters) {
            String strTemp = isfilterOperatorIsInAndVauleIsNull(filter);
            strSearch = strSearch + strTemp;
            strSearch = strSearch
                    + findFilerParameter1(operat,
                            aliases + filter.getProperty(), filter,
                            filter.getValue());
        }
        return strSearch;
    }

    public String findFilterStr1AddAndOperatOrOperat(Pageable pageable,
            String aliases) {
        List<Filter> filters = pageable.getFilters();
        String strSearch = filterCreate(filters, aliases, " and ");
        List<Filter> filters2 = pageable.getOrFilters();
        strSearch = strSearch + filterCreate(filters2, aliases, " or ");
        return strSearch;
    }

    public String findParameterName(Filter filter) {
        String vauleStr = "";
        if (filter.getValue() != null) {
            vauleStr = filter.getValue().toString().replaceAll("[^\\w]", "");
        }
        return findLastProperty(filter.getProperty())
                + isDatetoyyyymmdd(filter.getValue())
                + retOperatorToString(filter) + vauleStr;
    }

    private String filterCreate(List<Filter> filters, String aliases,
            String operat, String tableName) {
        String strSearch = "";
        for (Filter filter : filters) {
            if (isCollectionProperty(tableName, filter)) {
                strSearch = strSearch
                        + findFilerParameter_NoAliases(operat,
                                filter.getProperty(), filter, filter.getValue());
                continue;
            }
            String strTemp = isfilterOperatorIsInAndVauleIsNull(filter);
            strSearch = strSearch + strTemp;
            strSearch = strSearch
                    + findFilerParameter1(operat,
                            aliases + filter.getProperty(), filter,
                            filter.getValue());
        }
        return strSearch;
    }

    public String findFilterStr1AddAndOperatOrOperat(Pageable pageable,
            String aliases, String tableName) {
        List<Filter> filters = pageable.getFilters();
        String strSearch = filterCreate(filters, aliases, " and ", tableName);
        List<Filter> filters2 = pageable.getOrFilters();
        strSearch = strSearch
                + filterCreate(filters2, aliases, " or ", tableName);
        return strSearch;
    }


    public Query transferParameter(Query query,
            Pageable pageable) {
        List<Filter> filters = pageable.getFilters();
        if (pageable.getOrFilters() != null
                && pageable.getOrFilters().size() > 0) {
            filters.addAll(pageable.getOrFilters());
        }
        for (Filter filter : filters) {
            if (!isfilterOperatorIsNullOrisNotNull(filter)) {
                query.setParameter(findParameterName(filter), filter.getValue());
            }
        }
        return query;
    }

    /**
     * 需要判断时间
     * 
     * @param time
     * @return
     */
    public static String dealTime(int time) {
        if (time > 1440) {
            return time / 1440 + "天";
        } else if (time > 0) {
            return time / 60 + "小时";
        } else {
            return "长期有效";
        }
    }
}
