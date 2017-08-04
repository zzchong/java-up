package train;

/**
 * Created by pc on 2017/8/4.
 */
public class Route {

    private String startTown;

    private String endTown;

    private Double edge;

    public Route(String startTown, String endTown, Double edge) {
        this.startTown = startTown;
        this.endTown = endTown;
        this.edge = edge;
    }

    public String getStartTown() {
        return startTown;
    }

    public void setStartTown(String startTown) {
        this.startTown = startTown;
    }

    public String getEndTown() {
        return endTown;
    }

    public void setEndTown(String endTown) {
        this.endTown = endTown;
    }

    public Double getEdge() {
        return edge;
    }

    public void setEdge(Double edge) {
        this.edge = edge;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (startTown != null ? !startTown.equals(route.startTown) : route.startTown != null) return false;
        return endTown != null ? endTown.equals(route.endTown) : route.endTown == null;
    }

    @Override
    public int hashCode() {
        int result = startTown != null ? startTown.hashCode() : 0;
        result = 31 * result + (endTown != null ? endTown.hashCode() : 0);
        return result;
    }
}
