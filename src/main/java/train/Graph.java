package train;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pc on 2017/8/4.
 */
public class Graph {

    private List<Route> routes;

    private List  minRoutes;

    public Graph(List<Route> routes) {
        this.routes = routes;
    }

    Route getDirectRoute(String startTown,String endTown){

        if(routes.isEmpty())
            return  null;

        Optional<String> start = Optional.ofNullable(startTown);
        Optional<String> end = Optional.ofNullable(endTown);

        if(start.isPresent() && end.isPresent()) {
            List<Route> givenRoutes = routes.stream()
                    .filter(route -> route.getStartTown().equals(start.get()) && route.getEndTown().equals(end.get()))
                    .collect(Collectors.toList());

            if(!givenRoutes.isEmpty() && givenRoutes.size()>0)
                return givenRoutes.get(0);
            return null;
        }

        return  null;
    }

    void  getRoutes(String startTown,String endTown){

        if(routes.isEmpty())
            return;
        Optional<String> start = Optional.ofNullable(startTown);
        Optional<String> end = Optional.ofNullable(endTown);

        if(start.isPresent() && end.isPresent()) {
//            Map<String,LinkedList<Route>>
//            List<Route> startRoutes = routes.stream().filter(route -> start.get().equals(route.getStartTown())).collect(Collectors.toList());
//            startToutes.stream()
//                    .flatMap(route -> routes.stream().filter(route1 -> route1.getStartTown().equals(route.getEndTown())))

        }

    }

    List<Route> getNextRoutes(Route route){
        return null;
    }


    String getDistance(String ...towns){
        Double distance= 0d;
        Boolean isExist = true;
        if (towns.length>1){
            for (int i = 0;i<towns.length;i++){
                int j = i+1;
                if(i==towns.length-1)
                    break;

                Route route = getDirectRoute(towns[i],towns[j]);
                if(route==null){
                    isExist = false;
                    break;
                }
                distance += route.getEdge();
            }
        }

        return  isExist?distance.toString():"NO SUCH ROUTE";
    }

}
