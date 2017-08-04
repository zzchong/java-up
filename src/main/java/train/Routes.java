package train;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

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

    public Routes(String fromTown, String toTown) {
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

    List<LinkedList<Route>> getRoutesByStop(Integer stop){
        return  getRoutesByStop(stop,"=");
    }

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


    List<Double> getLength(){
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
