import java.util.Comparator;

public class DynamicComparatorStreamuri implements Comparator<Stream> {
    private int compareBy = 0;

    public DynamicComparatorStreamuri() {
    }

    public int getCompareBy() {
        return compareBy;
    }

    public void setCompareBy(int compareBy) {
        this.compareBy = compareBy;
    }

    public int compare(Stream a, Stream b) {
        if (compareBy == 0) {
            return a.getNoOfStreams().compareTo(b.getNoOfStreams());
        }
        Long p =  (b.getDateAdded()-a.getDateAdded());
        if (p == 0) {
            return b.getNoOfStreams().compareTo(a.getNoOfStreams());
        }
        if (p > 0) return 1;
        else return -1;
    }
}
