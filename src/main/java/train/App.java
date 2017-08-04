package train;


import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pc on 2017/8/4.
 */
public class App {

    @Test
    public void trainTest(){

        List<Route> routes = Arrays.asList(
                new Route("A","B", (double) 5),
                new Route("B","C", (double) 4),
                new Route("C","D", (double) 8),
                new Route("D","C", (double) 8),
                new Route("D","E", (double) 6),
                new Route("A","D", (double) 5),
                new Route("C","E", (double) 2),
                new Route("E","B", (double) 3),
                new Route("A","E", (double) 7)
        );

        Graph graph = new Graph(routes);

        //1 A-B-C
        System.out.println(graph.getDistance("A","B","C"));
        //2 A-D
        System.out.println(graph.getDistance("A","D"));
        //3 A-D-C
        System.out.println(graph.getDistance("A","D","C"));
        //4 A-E-B-C-D
        System.out.println(graph.getDistance("A","E","B","C","D"));
        //5 A-E-D
        System.out.println(graph.getDistance("A","E","D"));

        //6
        System.out.println(graph.getRoutes("C","C").getRoutesByStop(3,"<=").size());

        //7
        System.out.println(graph.getRoutes("A","C").getRoutesByStop(4).size());

        //8
        System.out.println(graph.getRoutes("A","C").getMinLength());

        //9
        System.out.println(graph.getRoutes("B","B").getMinLength());

        //10
        System.out.println(graph.getRoutes("C","C").getNumByLength(30d,"<"));

    }
}
