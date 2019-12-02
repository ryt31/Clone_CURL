import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandRead {

    // コンソールからコマンドを取得しリストに格納し、それを返すメソッド
    public static List<String> CommandRead(){
        // コマンドのリスト
        List<String> commandList = new ArrayList<>();
        // コンソールからコマンドを読み込む
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try{
            System.out.print("コマンドを入力してください:");
            String str = bufferedReader.readLine(); // 1行読み込む
            String[] array = str.split(" "); // スペースで文字列を分解し配列化
            // コマンドリストに要素を追加
            for(int i=0;i < array.length;i++){
                commandList.add(array[i]);
            }
        } catch (IOException e){
            e.printStackTrace(); // エラー箇所を示す
        }
        return commandList;
    }

    // コマンドからURLを探すメソッド
    public static String SerachHttp(List<String> commandList){

        String url = ""; // URL

        for (int i=0;i<commandList.size();i++){
            // コマンドリストの要素にhttpから始まるものがあるなら
            if(commandList.get(i).startsWith("http")){
                url = commandList.get(i); // 要素を取り出しURLに代入
                commandList.remove(i); // リストから削除
            }
        }
        return  url;
    }

    // コマンドからファイル名を探すメソッド
    public static String SerachFileName(List<String> commandList){
        String fileName = ""; // URL

        try {
            for (int i = 0; i < commandList.size(); i++) {
                // コマンドリストの要素にhttpから始まるものがあるなら
                if (commandList.get(i).equals("-o")) {
                    fileName = commandList.get(i + 1); // 要素を取り出しURLに代入
                }
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("ファイルが正しく作られませんでした");
        }
        return  fileName;
    }

    // コマンドからキーを探すメソッド
    public static String SerachParam(List<String> commandList){
        String param = ""; // URL
        try {
            for (int i=0;i<commandList.size();i++){
                // コマンドリストの要素にhttpから始まるものがあるなら
                if(commandList.get(i).equals("-d")){
                    param = commandList.get(i+1); // 要素を取り出しURLに代入
                }
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("キーが正しくありません");
        }
        // ストリングビルダーに
        StringBuilder newParam = new StringBuilder(param);
        newParam.deleteCharAt(0); // 最初のの文字を削除
        newParam.deleteCharAt(newParam.length()-1); // 最後の文字を削除
        return  newParam.toString();
    }
}
