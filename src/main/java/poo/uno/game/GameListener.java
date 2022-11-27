package poo.uno.game;

import java.util.EventListener;

public interface GameListener extends EventListener {
	void notify(GameEvent event);
}
