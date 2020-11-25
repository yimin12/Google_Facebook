package Object_Orient_Design.Hotel_Design;

import java.util.Date;
import java.util.Objects;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 18:22
 *   @Description :
 *
 */
public class SearchRequest {

    private Date startDate;
    private Date endDate;
    public SearchRequest(Date startDate, Date endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchRequest that = (SearchRequest) o;
        return Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
