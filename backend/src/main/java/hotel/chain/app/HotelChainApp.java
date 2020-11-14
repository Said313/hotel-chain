package hotel.chain.app;

import hotel.chain.app.controllers.authorization.AuthorizationService;
import hotel.chain.app.controllers.bookings.BookingService;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/services")
public class HotelChainApp extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public HotelChainApp() {
        singletons.add(new AuthorizationService());
        singletons.add(new BookingService());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
