package uz.suem.psycho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MoodEvalActivity extends Activity implements View.OnClickListener {
    TextView question;
    Button BtnYes, BtnNo, BtnConversely;

    List<Integer> chain = new ArrayList<>();
    String result = "В момент обследования преобладает ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_evaluation);

        init();
    }

    public void init() {
        question = findViewById(R.id.question);
        BtnYes = findViewById(R.id.yes);
        BtnYes.setOnClickListener(this);

        BtnNo = findViewById(R.id.no);
        BtnNo.setOnClickListener(this);

        BtnConversely = findViewById(R.id.conversely);
        BtnConversely.setOnClickListener(this);

        changeQuestion();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes:
                chain.add(1);
                break;

            case R.id.no:
                chain.add(2);
                break;
            case R.id.conversely:
                chain.add(3);
                break;
        }
        changeQuestion();
    }

    public void changeQuestion() {
        switch (chain.size()) {
            case 0:
                question.setText("1.\tЧувствую себя исключительно бодро");
                break;
            case 1:
                question.setText("2.\tСоседи (другие учащиеся и т. п.) очень мне надоели");
                break;
            case 2:
                question.setText("3.\tИспытываю какое-то тягостное чувство");
                break;
            case 3:
                question.setText("4.\tСкорее бы испытать чувство покоя (закончились бы урок, занятия, четверть и т. п.)");
                break;
            case 4:
                question.setText("5.\tОставили бы меня в покое, не беспокоили бы");
                break;
            case 5:
                question.setText("6.\tСостояние такое, что, образно говоря, готов горы свернуть");
                break;
            case 6:
                question.setText("7.\tОценка по тесту неприятна, вызывает чувство неудовлетворенности");
                break;
            case 7:
                question.setText("8.\tУдивительное настроение: хочется петь и плясать, целовать от радости каждого, кого вижу");
                break;
            case 8:
                question.setText("9.\tВокруг меня очень много людей, способных поступить неблагородно, сделать зло. От любого человека можно ожидать неблаговидного поступка");
                break;
            case 9:
                question.setText("10.\tВсе здания вокруг, все постройки на улицах кажутся мне удивительно неудачными");
                break;
            case 10:
                question.setText("11.\tКаждому, кого встречаю, способен сказать грубость");
                break;
            case 11:
                question.setText("12.\tИду радостно, не чувствую под собой ног");
                break;
            case 12:
                question.setText("13.\tНикого не хочется видеть, ни с кем не хочется разговаривать");
                break;
            case 13:
                question.setText("14.\tНастроение такое, что хочется сказать: «Пропади все пропадом!»");
                break;
            case 14:
                question.setText("15.\tХочется сказать: «Перестаньте меня беспокоить, отвяжитесь!»");
                break;
            case 15:
                question.setText("16.\tВсе люди без исключения мне кажутся чрезвычайно благожелательными, хорошими. Все они без исключения мне симпатичны.");
                break;
            case 16:
                question.setText("17.\tНе вижу впереди никаких трудностей. Все легко! Все доступно!");
                break;
            case 17:
                question.setText("18.\tМое будущее мне кажется очень печальным");
                break;
            case 18:
                question.setText("19.\tБывает хуже, но редко");
                break;
            case 19:
                question.setText("20.\tНе верю даже самым близким людям");
                break;
            case 20:
                question.setText("21.\tАвтомашины гудят на улице резко, но зато эти звуки воспринимаются как приятная музыка");
                break;
            default:
                Intent intent = new Intent(this, Results.class);
                intent.putExtra("test_name", "ТЕСТBОПРОСНИК «ОЦЕНКА НАСТРОЕНИЯ»");
                intent.putExtra("result", result + calculate());
                startActivity(intent);
                finish();
                break;
        }
    }

    public String calculate() {
        int[] sum = new int[3];

        //обычное настроение
        Integer count = 0;
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i) == 2) {
                count++;
            }
        }

        if (count == 20)
            sum[0] = 9;
        else if (count == 19)
            sum[0] = 8;
        else if (count == 18)
            sum[0] = 7;
        else if (count == 17 || count == 16)
            sum[0] = 6;
        else if (count >= 13 && count <= 15)
            sum[0] = 5;
        else if (count >= 10 && count <= 12)
            sum[0] = 4;
        else if (count == 8 || count == 9)
            sum[0] = 3;
        else if (count == 6 || count == 7)
            sum[0] = 2;
        else if (count <= 5)
            sum[0] = 1;

        //Астеническое состояние
        count = 0;
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i) == 1) {
                if ((i == 2) || (i == 3) || (i == 4) || (i == 5) || (i == 7) || (i == 9) || (i == 10) || (i == 11) || (i == 13) || (i == 14) || (i == 15) || (i == 18) || (i == 19) || (i == 20))
                    count++;
            } else if (chain.get(i) == 3) {
                if ((i == 1) || (i == 6) || (i == 8) || (i == 12) || (i == 16) || (i == 17)) {
                    count++;
                }
            }
        }

        if (count == 1 || count == 2)
            sum[1] = 9;
        else if (count == 3)
            sum[1] = 8;
        else if (count == 4)
            sum[1] = 7;
        else if (count == 5 || count == 6)
            sum[1] = 6;
        else if (count == 7 || count == 8)
            sum[1] = 5;
        else if (count == 9 || count == 10)
            sum[1] = 4;
        else if (count >= 11 || count <= 13)
            sum[1] = 3;
        else if (count == 14 || count == 15)
            sum[1] = 2;

        //Состояние эйфории
        count = 0;
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i) == 1) {
                if ((i == 1) || (i == 6) || (i == 8) || (i == 12) || (i == 16) || (i == 17))
                    count++;
            } else if (chain.get(i) == 3) {
                if ((i == 2) || (i == 3) || (i == 4) || (i == 5) || (i == 7) || (i == 9) || (i == 10) || (i == 11) || (i == 13) || (i == 14) || (i == 15) || (i == 18) || (i == 19) || (i == 20)) {
                    count++;
                }
            }
        }

        if (count <= 6)
            sum[2] = 9;
        else if (count == 7)
            sum[2] = 8;
        else if (count == 8 || count == 9)
            sum[2] = 7;
        else if (count == 10 || count == 11)
            sum[2] = 6;
        else if (count == 12 || count == 13)
            sum[2] = 5;
        else if (count == 14 || count == 15)
            sum[2] = 4;
        else if (count == 16 || count == 17)
            sum[2] = 3;
        else if (count == 18 || count == 19)
            sum[2] = 2;
        else if (count >= 20)
            sum[2] = 1;

        Arrays.sort(sum);

        if (sum[sum.length - 1] == sum[0]) {
            return "обычное состоянпе.";
        } else if (sum[sum.length - 1] == sum[1])
            return "негативное (астеническое) состояние.";
        else
            return "cостояние эйфории";
    }
}
