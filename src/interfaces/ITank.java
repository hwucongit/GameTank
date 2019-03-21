package interfaces;

import model.EnemyTank;
import model.Item;
import model.MyTank;

import java.util.List;

public interface ITank {
    List<Item> getItems();
    List<EnemyTank> getEnemyTank();
    MyTank getMyTank();
}
