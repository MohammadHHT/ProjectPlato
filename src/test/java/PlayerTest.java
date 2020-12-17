import model.Player;
import model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PlayerTest {

    @Test
    public void addNewPlayer() {
        Player player = new Player("firstName", "lastName",
                "userName", "password",
                "example@exp.exp", "09000000000");
        ArrayList<User> players = new ArrayList<>();
        Player.addPlayers(players);

        Assert.assertEquals(Player.getPlayers().get("userName").getUsername(), "userName");
        Assert.assertEquals(Player.getPlayers().size(), 1);
    }
}
