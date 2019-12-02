import java.util.List;

public class Curl {

    public static void main(String[] args) {
        boolean isEnd = false; // プログラムの終了フラグ

        while (isEnd == false) {
            // コマンドリストを格納
            List<String> commandList = CommandRead.CommandRead();
            // コマンドによってプリントするものを分岐させquitが入力されたら終了
            isEnd = ControlMethod.ControlMethod(commandList,isEnd);
        }
    }
}
