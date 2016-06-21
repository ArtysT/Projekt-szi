
package LogicalLayer;

import java.util.List;

public interface OnMoveListener {
	/**
	 * W tej metodzie zaimplementuj reakcj� na ruch kelnera.
	 * @param path �cie�ka, kt�r� ma przej�� kelner.
	 */
	public void onMove(List<Coordinates> path);
}
