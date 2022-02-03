package Model;

import java.util.List;

public interface Strategy {

    void addClient(List<Queue> queues, Client c);

}
