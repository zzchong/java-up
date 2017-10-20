package train;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Raytine on 2017/8/4.
 */
public class Routes {

    private String fromTown;

    private String toTown;

    private List<LinkedList<Route>> routes;

    public Routes(String fromTown, String toTown, List<LinkedList<Route>> routes) {
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.routes = routes;
    }

     Routes(String fromTown, String toTown) {
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.routes = new ArrayList<>();
    }

    public String getFromTown() {
        return fromTown;
    }

    public void setFromTown(String fromTown) {
        this.fromTown = fromTown;
    }

    public String getToTown() {
        return toTown;
    }

    public void setToTown(String toTown) {
        this.toTown = toTown;
    }

    public List<LinkedList<Route>> getRoutes() {
        return routes;
    }

    public void setRoutes(List<LinkedList<Route>> routes) {
        this.routes = routes;
    }

    public Integer size(){
        if(!routes.isEmpty())
            return routes.size();
        return 0;
    }

    /**
     * 根据停站点查询线路
     * @param stop
     * @return
     */
     List<LinkedList<Route>> getRoutesByStop(Integer stop,String operation){
        if(!routes.isEmpty()){
            return routes.stream()
                    .filter(
                            linkedList -> {
                                switch (operation){
                                    case "=":
                                        return  linkedList.size()==stop;
                                    case ">":
                                        return linkedList.size()>stop;
                                    case ">=":
                                        return linkedList.size()>=stop;
                                    case "<=":
                                        return linkedList.size()<=stop;
                                    case "<>":
                                        return linkedList.size()!=stop;
                                    default:
                                        return linkedList.size()==stop;
                                }
                            })
                    .collect(Collectors.toList());
        }
        return  null;
    }

    /**
     * 根据停站点查询线路
     * @param stop
     * @return
     */
    List<LinkedList<Route>> getRoutesByStop(Integer stop){
        return  getRoutesByStop(stop,"=");
    }


    /**
     * 查询两城之间最大或最小的距离
     * @param maxOrMin
     * @return
     */

    Double getMaxOrMinLength (String maxOrMin){

        List<Double> doubles = getLength();
        if(!routes.isEmpty()){
                if (!doubles.isEmpty()){
                  return "min".equals(maxOrMin)
                          ?
                          doubles.parallelStream().min(Comparator.naturalOrder()).orElse(0d)
                          :
                          doubles.parallelStream().max(Comparator.naturalOrder()).orElse(0d);
                }

        }
        return 0d;
    }

    Double getMaxLength (){
        return getMaxOrMinLength("max");
    }

    Double getMinLength (){
        return getMaxOrMinLength("min");
    }


    /**
     * 根据距离查线路数
     * @param length
     * @param operation
     * @return
     */

    Long getNumByLength(Double length,String operation){
        List<Double> doubles = getLength();
        if(!doubles.isEmpty()){
         Long count = doubles.stream()
                    .filter(aDouble -> {
                        switch (operation){
                            case "=":return Objects.equals(aDouble, length);
                            case "<":return aDouble<length;
                            case ">":return aDouble>length;
                            case "<=":return aDouble<=length;
                            case ">=":return aDouble>=length;
                            case "<>":return !Objects.equals(length, aDouble);
                            default:return Objects.equals(aDouble, length);
                        }
                    })
                    .count();
         return count;
        }
        return 0L;
    }


    private List<Double> getLength(){
        if(!routes.isEmpty()) {
            List<Double> doubles = new LinkedList<>();
            routes
                    .forEach(linkedList -> {
                                doubles.add(linkedList.stream().map(Route::getEdge).reduce(0d, Double::sum));
                            }
                    );
            return  doubles;
        }
        return null;
    }

    @Override
    public String toString() {
        return fromTown+"-"+toTown;
    }
}
