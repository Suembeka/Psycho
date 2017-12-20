package uz.suem.psycho;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LyusherActivity extends Activity implements View.OnClickListener {
    List<CC> fields = new ArrayList<>();
    TextView timer;
    int[] colors;
    String result = "";

    // + + * * = = - -
    List<Integer> colorSequence1 = new ArrayList<>();
    List<Integer> colorSequence2 = new ArrayList<>();
    boolean next = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyusher);

        initViews();
        setViewsColor();
    }

    void initViews() {
        colors = getResources().getIntArray(R.array.allCoors);

        List<View> textViews = new ArrayList<>(8);
        textViews.add(findViewById(R.id.textView1));
        textViews.add(findViewById(R.id.textView2));
        textViews.add(findViewById(R.id.textView3));
        textViews.add(findViewById(R.id.textView4));
        textViews.add(findViewById(R.id.textView5));
        textViews.add(findViewById(R.id.textView6));
        textViews.add(findViewById(R.id.textView7));
        textViews.add(findViewById(R.id.textView8));

        for (int i = 0; i < 8; i++) {
            fields.add(new CC(textViews.get(i), 0));
        }

        timer = findViewById(R.id.timer);
        timer.setVisibility(View.INVISIBLE);
    }

    void setViewsColor() {
        int[] c = colors;
        shuffleArray(c);

        for (int i = 0; i < 8; i++) {
            fields.get(i).textView.setBackgroundColor(c[i]);
            fields.get(i).color = c[i];
            fields.get(i).textView.setOnClickListener(this);
        }
    }

    static void shuffleArray(int[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView1:
                fields.get(0).textView.setVisibility(View.INVISIBLE);
                if (next) colorSequence1.add(fields.get(0).color);
                else colorSequence2.add(fields.get(0).color);
                break;
            case R.id.textView2:
                fields.get(1).textView.setVisibility(View.INVISIBLE);
                if (next) colorSequence1.add(fields.get(1).color);
                else colorSequence2.add(fields.get(1).color);
                break;
            case R.id.textView3:
                fields.get(2).textView.setVisibility(View.INVISIBLE);
                if (next) colorSequence1.add(fields.get(2).color);
                else colorSequence2.add(fields.get(2).color);
                break;
            case R.id.textView4:
                fields.get(3).textView.setVisibility(View.INVISIBLE);
                if (next) colorSequence1.add(fields.get(3).color);
                else colorSequence2.add(fields.get(3).color);
                break;
            case R.id.textView5:
                fields.get(4).textView.setVisibility(View.INVISIBLE);
                if (next) colorSequence1.add(fields.get(4).color);
                else colorSequence2.add(fields.get(4).color);
                break;
            case R.id.textView6:
                fields.get(5).textView.setVisibility(View.INVISIBLE);
                if (next) colorSequence1.add(fields.get(5).color);
                else colorSequence2.add(fields.get(5).color);
                break;
            case R.id.textView7:
                fields.get(6).textView.setVisibility(View.INVISIBLE);
                if (next) colorSequence1.add(fields.get(6).color);
                else colorSequence2.add(fields.get(6).color);
                break;
            case R.id.textView8:
                fields.get(7).textView.setVisibility(View.INVISIBLE);
                if (next) colorSequence1.add(fields.get(7).color);
                else colorSequence2.add(fields.get(7).color);
                break;
        }

        System.out.println("colorSequence1.size() = " + colorSequence1.size());
        System.out.println("colorSequence2.size() = " + colorSequence2.size());
        if (((colorSequence1.size() == 8) && next) || ((colorSequence2.size() == 8) && !next)) {
            if (next) {
                next = false;
                timer.setVisibility(View.VISIBLE);
                new CountDownTimer(1000, 1000) {

                    @SuppressLint("SetTextI18n")
                    public void onTick(long millisUntilFinished) {
                        timer.setText("" + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        timerOf();
                    }
                }.start();
            } else {
                interpretation();
            }
        }
    }

    public void timerOf() {
        timer.setVisibility(View.INVISIBLE);
        for (int i = 0; i < 8; i++) {
            fields.get(i).textView.setVisibility(View.VISIBLE);
        }
        setViewsColor();
    }

    public void interpretation() {
        List<List<Integer>> sequences = new ArrayList<>();
        sequences.add(colorSequence1);
        sequences.add(colorSequence2);

        for (int i = 0; i < sequences.size(); i++) {
            if (i == 0)
                result += "Желаемое состояние: \n";
            else
                result += "Действительное состояние:\n";
            // + +
            if (sequences.get(i).get(0) == colors[0]) {
                if (sequences.get(i).get(1) == colors[1])
                    result += " - чувство удовлетворенности, спокойствия, стремление к спокойной обстановке, нежелание участвовать в конфликтах, стрессе.\n";
                if (sequences.get(i).get(1) == colors[2])
                    result += " - чувство целостности, активное и не всегда осознанное стремление к тесным отношениям. Потребность во внимании со стороны других.\n";
                if (sequences.get(i).get(1) == colors[4])
                    result += " - небольшое беспокойство, потребность в тонком окружении, стремление к эстетическому.\n";
                if (sequences.get(i).get(1) == colors[5])
                    result += " - чувство беспокойства, страх одиночества, стремление уйти от конфликта, избежать стресса.\n";
                if (sequences.get(i).get(1) == colors[6])
                    result += " - негативное состояние, стремление к покою, отдыху, неудовлетворенность отношением к себе, негативное отношение к ситуации.\n";
                if (sequences.get(i).get(1) == colors[7])
                    result += " - негативное состояние, потребность освободиться от стресса, стремление к покою, отдыху.\n";
            }

            if (sequences.get(i).get(0) == colors[1]) {
                if (sequences.get(i).get(1) == colors[0])
                    result += " - позитивное состояние, стремление к признанию, к деятельности, обеспечивающей успех.\n";
                if (sequences.get(i).get(1) == colors[2])
                    result += " - активное стремление к успеху, к самостоятельным решениям, преодолению преград в деятельности.\n";
                if (sequences.get(i).get(1) == colors[3])
                    result += " - небольшое беспокойство, стремление к признанию, популярности, желание произвести впечатление.\n";
                if (sequences.get(i).get(1) == colors[4])
                    result += " - небольшое беспокойство, стремление к признанию, популярности, желание супервпечатлений, повышенное внимание к реакциям окружающих на свои поступки.\n";
                if (sequences.get(i).get(1) == colors[5])
                    result += " - чувство неудовлетворенности, усталости, переоценка значимости отношения к себе со стороны окружающих.\n";
                if (sequences.get(i).get(1) == colors[6])
                    result += " - чувство обиды, злости, стремление к жалости, авторитарности в отношениях.\n";
                if (sequences.get(i).get(1) == colors[7])
                    result += " - чувство неудовлетворенности, стремление к признанию, желание произвести впечатление.\n";
            }
            if (sequences.get(i).get(0) == colors[2]) {
                if (sequences.get(i).get(1) == colors[0])
                    result += " - деловое возбуждение, активное стремление к деятельности, впечатлениям, удовольствиям.\n";
                if (sequences.get(i).get(1) == colors[1])
                    result += " - деловое возбуждение, активное стремление к цели, преодоление всех трудностей, стремление к высокой оценке своей деятельности.\n";
                if (sequences.get(i).get(1) == colors[3])
                    result += " - деловое, слегка повышенное возбуждение, увлеченность, оптимизм, стремление к контактам, расширение сферы деятельности.\n";
                if (sequences.get(i).get(1) == colors[4])
                    result += " - повышенное возбуждение, не всегда адекватная увлеченность, стремление произвести впечатление.\n";
                if (sequences.get(i).get(1) == colors[5])
                    result += " - негативное настроение, огорчение из-за неудачи, нежелание лишиться благоприятной ситуации.\n";
                if (sequences.get(i).get(1) == colors[6])
                    result += " - негативное настроение, злость, стремление уйти из неблагоприятной ситуации.\n";
                if (sequences.get(i).get(1) == colors[7])
                    result += " - чувство неудовлетворенности, направленность на рискованное действие.\n";
            }

            if (sequences.get(i).get(0) == colors[3]) {
                if (sequences.get(i).get(1) == colors[0])
                    result += " - настроение в общем положительное, стремление к позитивному эмоциональному состоянию, выдержке.\n";
                if (sequences.get(i).get(1) == colors[1])
                    result += " - настроение в общем положительное, желание поиска верных путей решения стоящих задач, стремление к самоутверждению.\n";
                if (sequences.get(i).get(1) == colors[2])
                    result += " - несколько повышенное деловое возбуждение, стремление к широкой активности.\n";
                if (sequences.get(i).get(1) == colors[4])
                    result += " - небольшая эйфория, стремление к ярким событиям, желание произвести впечатление.\n";
                if (sequences.get(i).get(1) == colors[5])
                    result += " - негативное настроение, огорчение и потребность в эмоциональной разрядке, отдыхе.\n";
                if (sequences.get(i).get(1) == colors[6])
                    result += " - весьма негативное настроение, стремление уйти от любых проблем, склонность к необходимым, малоадекватным решениям.\n";
                if (sequences.get(i).get(1) == colors[7])
                    result += " - негативное угнетенное состояние, стремление выйти из неприятной ситуации, нечеткое представление о том, как это сделать.\n";
            }
            if (sequences.get(i).get(0) == colors[4]) {
                if (sequences.get(i).get(1) == colors[0])
                    result += " - неопределенное настроение, стремление к согласию и гармонии.\n";
                if (sequences.get(i).get(1) == colors[1])
                    result += " - настороженность, желание произвести впечатление.\n";
                if (sequences.get(i).get(1) == colors[2])
                    result += " - некоторое возбуждение, увлеченность, активное стремление произвести впечатление.\n";
                if (sequences.get(i).get(1) == colors[3])
                    result += " - возбуждение, фантазирование, стремление к ярким событиям.\n";
                if (sequences.get(i).get(1) == colors[5])
                    result += " - возбуждение, направленность на сильные эмоциональные переживания.\n";
                if (sequences.get(i).get(1) == colors[6])
                    result += " - негативное состояние.\n";
                if (sequences.get(i).get(1) == colors[7])
                    result += " - напряжение, стремление оградить себя от конфликтов, стресса.\n";
            }
            if (sequences.get(i).get(0) == colors[5]) {
                if (sequences.get(i).get(1) == colors[0])
                    result += " - напряжение, страх одиночества, желание выйти из неблагоприятной ситуации.\n";
                if (sequences.get(i).get(1) == colors[1])
                    result += " - чувство беспокойства, стремление к строгому контролю над собой, чтобы избежать ошибки.\n";
                if (sequences.get(i).get(1) == colors[2])
                    result += " - активное стремление к эмоциональной разрядке.\n";
                if (sequences.get(i).get(1) == colors[3])
                    result += " - утрата веры в положительные перспективы, вероятность необдуманных решений («мне все равно»).\n";
                if (sequences.get(i).get(1) == colors[4])
                    result += " - чувство неудовлетворенности, стремление к комфорту.\n";
                if (sequences.get(i).get(1) == colors[6])
                    result += " - негативное состояние, разочарованность, стремление к покою, желание уйти от активности.\n";
                if (sequences.get(i).get(1) == colors[7])
                    result += " - весьма негативное состояние, стремление уйти от сложных проблем, а не бороться с ними.\n";
            }
            if (sequences.get(i).get(0) == colors[6]) {
                if (sequences.get(i).get(1) == colors[0])
                    result += " - весьма негативное состояние, стремление уйти от проблем («оставили бы в покое»).\n";
                if (sequences.get(i).get(1) == colors[1])
                    result += " - возбуждение, гневное отношение к окружающим, не всегда адекватное упрямство.\n";
                if (sequences.get(i).get(1) == colors[2])
                    result += " - сильное возбуждение, возможны аффективные поступки.\n";
                if (sequences.get(i).get(1) == colors[3])
                    result += " - весьма негативное состояние, отчаяние, суицидные мысли.\n";
                if (sequences.get(i).get(1) == colors[4])
                    result += " - напряженность, мечты о гармонии.\n";
                if (sequences.get(i).get(1) == colors[5])
                    result += " - возбуждение, постановка нереальных задач, стремление уйти от беспокойных мыслей, неблагоприятных ситуаций.\n";
                if (sequences.get(i).get(1) == colors[7])
                    result += " - чувство безнадежности, обреченности, стремление сопротивляться всему, неадекватность.\n";
            }
            if (sequences.get(i).get(0) == colors[7]) {
                if (sequences.get(i).get(1) == colors[0])
                    result += " - негативное состояние, желание спокойной ситуации.\n";
                if (sequences.get(i).get(1) == colors[1])
                    result += " - негативное состояние, ощущение враждебности окружающих и желание оградиться от среды.\n";
                if (sequences.get(i).get(1) == colors[2])
                    result += " - негативное состояние, повышенные требования к окружающим, не всегда адекватная активность.\n";
                if (sequences.get(i).get(1) == colors[3])
                    result += " - негативное состояние, стремление уйти от проблем, а не решить их.\n";
                if (sequences.get(i).get(1) == colors[4])
                    result += " - чувство беспокойства, настороженности, стремление скрыть это чувство.\n";
                if (sequences.get(i).get(1) == colors[5])
                    result += " - весьма негативное состояние, стремление уйти от всего сложного, трудного, волнующего.\n";
                if (sequences.get(i).get(1) == colors[6])
                    result += " - весьма негативное состояние, обида, чувство угнетенности, вероятность неадекватных решений.\n";
            }

            // - -
            if (sequences.get(i).get(6) == colors[0]) {
                if (sequences.get(i).get(7) == colors[1])
                    result += " - сильное напряжение, стремление избавиться от негативного стрессового состояния.\n";
                if (sequences.get(i).get(7) == colors[2])
                    result += " - сильное напряжение, чувство беспомощности, желание выйти из эмоциональной ситуации.\n";
                if (sequences.get(i).get(7) == colors[3])
                    result += " - состояние, близкое к стрессу, эмоциональные негативные переживания, чувство беспомощности.\n";
                if (sequences.get(i).get(7) == colors[4])
                    result += " - состояние, близкое к стрессу, сложность взаимоотношений.\n";
                if (sequences.get(i).get(7) == colors[5])
                    result += " - эмоциональная неудовлетворенность, самоограничение, поиск поддержки.\n";
                if (sequences.get(i).get(7) == colors[6])
                    result += " - состояние, близкое к стрессу, эмоциональная неудовлетворенность, стремление выйти из психогенной ситуации.\n";
                if (sequences.get(i).get(7) == colors[7])
                    result += " - несколько угнетенное состояние, тревожность, ощущение бесперспективности.\n";
            }
            if (sequences.get(i).get(6) == colors[1]) {
                if (sequences.get(i).get(7) == colors[0])
                    result += " - угнетенное состояние, неверие в свои силы, стремление выйти из неприятной ситуации.\n";
                if (sequences.get(i).get(7) == colors[2])
                    result += " - сильное возбуждение, тягостные переживания, отношения со средой считает для себя враждебными, возможны аффективные поступки.\n";
                if (sequences.get(i).get(7) == colors[3])
                    result += " - состояние, близкое к фрустрации, чувство разочарования, нерешительности.\n";
                if (sequences.get(i).get(7) == colors[4])
                    result += " - состояние, близкое к стрессовому, чувство оскорбленного достоинства, неверие в свои силы.\n";
                if (sequences.get(i).get(7) == colors[5])
                    result += " - состояние, близкое к стрессовому, неадекватно повышенный самоконтроль, необоснованное стремление к признанию.\n";
                if (sequences.get(i).get(7) == colors[6])
                    result += " - состояние фрустрации из-за ограничения амбициозных требований, недостаточная целеустремленность.\n";
                if (sequences.get(i).get(7) == colors[7])
                    result += " - состояние фрустрации, раздраженность из-за ряда неудач, снижение волевых качеств.\n";
            }
            if (sequences.get(i).get(6) == colors[2]) {
                if (sequences.get(i).get(7) == colors[0])
                    result += " - подавляемое возбуждение, раздражительность, нетерпеливость, поиск выхода из негативных отношений, сложившихся с близкими людьми.\n";
                if (sequences.get(i).get(7) == colors[1])
                    result += " - состояние стресса из-за неадекватной самооценки.\n";
                if (sequences.get(i).get(7) == colors[3])
                    result += " - мнительность, тревожность, неадекватная оценка среды, стремление к самооправданию.\n";
                if (sequences.get(i).get(7) == colors[4])
                    result += " - состояние стресса из-за неудачных попыток достичь взаимопонимания, чувство неуверенности, беспомощности, желание сочувствия.\n";
                if (sequences.get(i).get(7) == colors[5])
                    result += " - сильное напряжение, вызванное иногда сексуальным самоограничением, отсутствие дружеских контактов, неуверенность в своих силах.\n";
                if (sequences.get(i).get(7) == colors[6])
                    result += " - состояние стресса из-за глубокого разочарования, фрустрация, чувство тревожности, бессилия решить конфликтную проблему, желание выйти из фрустрирующей ситуации любым путем, сомнение в том, что это удастся.\n";
                if (sequences.get(i).get(7) == colors[7])
                    result += " - сдерживаемое возбуждение, чувство утрачиваемой перспективы, вероятность нервного истощения.\n";
            }
            if (sequences.get(i).get(6) == colors[3]) {
                if (sequences.get(i).get(7) == colors[0])
                    result += " - чувство разочарования, состояние, близкое к стрессу, стремление подавить негативные эмоции.\n";
                if (sequences.get(i).get(7) == colors[1])
                    result += " - состояние нерешительности, тревожности, разочарования.\n";
                if (sequences.get(i).get(7) == colors[6])
                    result += " - напряженность, чувство неуверенности, настороженности, стремление избежать контроля извне.\n";
                if (sequences.get(i).get(7) == colors[7])
                    result += " - напряженность, чувство боязни потерять что-то важное, упустить возможности, напряженное ожидание.\n";
            }
            if (sequences.get(i).get(6) == colors[4]) {
                if (sequences.get(i).get(7) == colors[0])
                    result += " - чувство неудовлетворенности, стимулирующее к активности; стремление к сотрудничеству.\n";
                if (sequences.get(i).get(7) == colors[1])
                    result += " - стрессовое состояния из-за неосуществившегося самоутверждения.\n";
                if (sequences.get(i).get(7) == colors[2])
                    result += " - стрессовое состояние из-за неудач в активных, иногда необдуманных действиях.\n";
                if (sequences.get(i).get(7) == colors[3])
                    result += " - настороженность, подозрительность, разочарование, замкнутость.\n";
                if (sequences.get(i).get(7) == colors[5])
                    result += " - стресс, вызванный нарушением желательных взаимоотношений, повышенная взыскательность к другим.\n";
                if (sequences.get(i).get(7) == colors[6])
                    result += " - напряжение из-за ограничения в самостоятельных решениях, стремление к взаимопониманию, откровенному выражению мысли.\n";
                if (sequences.get(i).get(7) == colors[7])
                    result += " - проявление нетерпения, но в то же время стремление к самоконтролю, что вызывает некоторое эмоциональное возбуждение.\n";
            }
            if (sequences.get(i).get(6) == colors[5]) {
                if (sequences.get(i).get(7) == colors[0])
                    result += " - негативное состояние, чувство неудовлетворенности из-за недостаточного признания заслуг (реальных и предполагаемых), стремление к самоограничению и самоконтролю.\n";
                if (sequences.get(i).get(7) == colors[1])
                    result += " - негативное состояние из-за чрезмерного самоконтроля, упрямое желание выделиться, сомнения в том, что это удастся.\n";
                if (sequences.get(i).get(7) == colors[2])
                    result += " - стрессовое состояние из-за подавленности эротических и других биологических потребностей, стремление к сотрудничеству для выхода из стресса.\n";
                if (sequences.get(i).get(7) == colors[3])
                    result += " - напряженность из-за стремления скрыть тревогу под маской уверенности и беспечности.\n";
                if (sequences.get(i).get(7) == colors[4])
                    result += " - негативное состояние из-за неудовлетворенного стремления к чувственной гармонии.\n";
                if (sequences.get(i).get(7) == colors[6])
                    result += " - стремление уйти из подчинения, негативное отношение к различным запретам.\n";
                if (sequences.get(i).get(7) == colors[7])
                    result += " - стрессовое состояние из-за подавления биологических сексуальных потребностей.\n";
            }
            if (sequences.get(i).get(6) == colors[6]) {
                if (sequences.get(i).get(7) == colors[0])
                    result += " - состояние беспокойства в связи со скрываемым вниманием получить помощь, поддержку.\n";
                if (sequences.get(i).get(7) == colors[1])
                    result += " - состояние, близкое к фрустрации из-за ограничения свободы желаемых действий, стремление избавиться от помех.\n";
                if (sequences.get(i).get(7) == colors[2])
                    result += " - стрессовое состояние, вызванное разочарованием в ожидаемой ситуации, эмоциональное возбуждение.\n";
                if (sequences.get(i).get(7) == colors[3])
                    result += " - стрессовое состояние из-за боязни дальнейших неудач, отказ от разумных компромиссов.\n";
                if (sequences.get(i).get(7) == colors[4])
                    result += " - поиски идеализированной ситуации.\n";
                if (sequences.get(i).get(7) == colors[5])
                    result += " - стрессовое состояние из-за неприятных ограничений, запретов, стремление сопротивляться ограничениям, уйти от заурядности.\n";
                if (sequences.get(i).get(7) == colors[7])
                    result += " - стремление выйти из неблагоприятной ситуации.\n";
            }
            if (sequences.get(i).get(6) == colors[7]) {
                if (sequences.get(i).get(7) == colors[0])
                    result += " - чувство неудовлетворенности, эмоциональной напряженности.\n";
                if (sequences.get(i).get(7) == colors[1])
                    result += " - эмоциональная напряженность, желание выйти из неблагоприятной ситуации.\n";
                if (sequences.get(i).get(7) == colors[2])
                    result += " - раздраженность, чувство беспомощности.\n";
                if (sequences.get(i).get(7) == colors[3])
                    result += " - тревожность, неуверенность в своих силах.\n";
                if (sequences.get(i).get(7) == colors[4])
                    result += " - небольшое контролируемое возбуждение.\n";
                if (sequences.get(i).get(7) == colors[5])
                    result += " - тревожность, неуверенность в своих силах, но при этом повышенная требовательность, желание достичь признания своей личности.\n";
                if (sequences.get(i).get(7) == colors[6])
                    result += " - отрицание каких-либо ограничений своей личности, активное стремление к деятельности.\n";
            }
        }
        startResultActivity();
    }

    public void startResultActivity() {
        Intent intent = new Intent(this, Results.class);
        intent.putExtra("test_name", "ТЕСТ ЛЮШЕРА");
        intent.putExtra("result", result);
        startActivity(intent);
        finish();
    }
}

