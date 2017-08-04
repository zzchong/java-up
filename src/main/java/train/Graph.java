package train;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pc on 2017/8/4.
 */
public class Graph {

    private List<Route> routes;

    private Map<String,Routes> cache = new ConcurrentHashMap<>();

    Graph(List<Route> routes) {
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

    Routes getRoutes(String startTown,String endTown){
        return  getRoutes(startTown,endTown,100,20);
    }

    Routes  getRoutes(String startTown,String endTown,Integer maxnumRoutes,Integer maxnumStop){
        if(routes.isEmpty())
            return null;
        if(cache.containsKey(startTown+"-"+endTown))
            return  cache.get(startTown+"-"+endTown);

        Optional<String> start = Optional.ofNullable(startTown);
        Optional<String> end = Optional.ofNullable(endTown);

        if(start.isPresent() && end.isPresent()) {
            Routes r = new Routes(startTown,endTown);
            List<LinkedList<Route>> returnList = new ArrayList<>();
            List<LinkedList<Route>> tempList = new ArrayList<>();

          routes.stream()
                  .filter(route -> route.getEndTown().equals(endTown))
                  .forEach(route -> {
                      LinkedList<Route> routeTemp = new LinkedList<>();
                      routeTemp.add(route);
                      if(route.getStartTown().equals(startTown))
                          returnList.add(routeTemp);
                      else
                          tempList.add(routeTemp);
                  });

            List<LinkedList<Route>> allRoutes = getPreviousRoutes(tempList,returnList,startTown,maxnumRoutes,maxnumStop);
            r.setRoutes(allRoutes);
            cache.put(startTown+"-"+endTown,r);
            return  r;
        }
        return  null;

    }

    List<LinkedList<Route>> getPreviousRoutes(List<LinkedList<Route>> tempList,List<LinkedList<Route>> returnList,String startTown,Integer maxnumRoutes,Integer maxnumStop){

        if(tempList.isEmpty() || tempList.size()==0)
            return  returnList;

        if(returnList.size()>=maxnumRoutes)
            return  returnList;

        boolean checks = returnList.stream()
                .anyMatch(linkedList -> linkedList.size()==maxnumStop);

        if(checks)
            return  returnList;


        List<LinkedList<Route>> copyTempList = new ArrayList<>();
        tempList
                .forEach(linkedList -> {
                    Route route = linkedList.getLast();
                    List<Route> previousRoutes = routes.stream()
                            .filter(route1 -> route1.getEndTown().equals(route.getStartTown()))
                            .collect(Collectors.toList());
                    if (previousRoutes.size() > 0) {
                        if (previousRoutes.size() == 1) {
                            LinkedList<Route> copyList = new LinkedList<>(linkedList);
                            Route previousRoute = previousRoutes.get(0);
                            copyList.add(previousRoute);
                                if (previousRoute.getStartTown().equals(startTown))
                                    returnList.add(copyList);
                                copyTempList.add(copyList);
                        } else {
                            previousRoutes.forEach(
                                    route1 -> {
                                            LinkedList<Route> copyList = new LinkedList<>(linkedList);
                                            copyList.add(route1);
                                            if (route1.getStartTown().equals(startTown))
                                                returnList.add(copyList);
                                            copyTempList.add(copyList);

                                    }
                            );
                        }
                    }
                });
        getPreviousRoutes(copyTempList,returnList,startTown,maxnumRoutes,maxnumStop);
        return returnList;
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
