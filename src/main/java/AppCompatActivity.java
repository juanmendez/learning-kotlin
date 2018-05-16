import org.jetbrains.annotations.NotNull;

public class AppCompatActivity extends Activity {
    private String nickname = "";
    static Integer NUMOFINSTANCES = 0;

    public AppCompatActivity(@NotNull String name, @NotNull Application application) {
        super(name, application);
        NUMOFINSTANCES++;
    }

    public String getNickname() {
        return nickname;
    }
}
