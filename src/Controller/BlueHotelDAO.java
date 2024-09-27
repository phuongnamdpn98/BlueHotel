package Controller;

import java.util.List;

public abstract class BlueHotelDAO<E, K> {
	
	abstract public boolean insert(E entity);

    abstract public void update(E entity);

    abstract public boolean delete(K key);

    abstract public List<E> selectAll();

    abstract public E selectById(K key);

    abstract public List<E> selectBySql(String sql, Object...args);
}
